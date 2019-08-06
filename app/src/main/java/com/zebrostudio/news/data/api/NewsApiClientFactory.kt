package com.zebrostudio.news.data.api

import com.zebrostudio.news.data.model.NewsDataEntity
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface NewsApiClientFactory {
  fun getNews(): Single<NewsDataEntity>
}

class NewsApiClientFactoryImpl : NewsApiClientFactory {

  private var retrofit: Retrofit? = null

  override fun getNews(): Single<NewsDataEntity> {
    if (retrofit == null) {
      retrofit = Retrofit.Builder()
        .baseUrl(UrlStore.NEWS_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    return retrofit!!.create(NewsApiClient::class.java).getNews()
  }
}