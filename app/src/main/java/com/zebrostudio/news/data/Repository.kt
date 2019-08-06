package com.zebrostudio.news.data

import com.zebrostudio.news.data.api.NewsApiClientFactory
import com.zebrostudio.news.data.database.DatabaseHelper
import com.zebrostudio.news.data.mapper.NewsDataEntityMapper
import com.zebrostudio.news.domain.model.NewsDomainModel
import com.zebrostudio.news.exceptions.CacheAbsentException
import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface Repository {
  fun getNews(): Single<NewsDomainModel>
}

class RepositoryImpl(
  private val newsApiClientFactory: NewsApiClientFactory,
  private val databaseHelper: DatabaseHelper,
  private val mapper: NewsDataEntityMapper,
  private val backgroundScheduler: BackgroundScheduler
) : Repository {

  override fun getNews(): Single<NewsDomainModel> {
    return newsApiClientFactory.getNews()
      .timeout(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS)
      .flatMap { newsDataEntity ->
        databaseHelper.clearCache()
        databaseHelper.saveToCache(newsDataEntity)
      }.flatMap {
        databaseHelper.getCachedNews()
      }.onErrorResumeNext {
        if (it is CacheAbsentException) {
          Single.error(it)
        } else {
          databaseHelper.getCachedNews()
        }
      }
      .map {
        mapper.mapFromDataEntity(it)
      }
      .subscribeOn(backgroundScheduler.getIoScheduler())
  }
}