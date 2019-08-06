package com.zebrostudio.news.presenter.mapper

import com.zebrostudio.news.domain.model.NewsDomainModel
import com.zebrostudio.news.presenter.model.ArticlesPresenterEntity
import com.zebrostudio.news.presenter.model.NewsPresenterEntity
import com.zebrostudio.news.presenter.model.SourcePresenterEntity

interface NewsPresenterEntityMapper {
  fun mapToPresenterEntity(newsDomainModel: NewsDomainModel): NewsPresenterEntity
}

class NewsPresenterEntityMapperImpl : NewsPresenterEntityMapper {
  override fun mapToPresenterEntity(newsDomainModel: NewsDomainModel): NewsPresenterEntity {
    return NewsPresenterEntity(
      newsDomainModel.status,
      newsDomainModel.totalResults,
      newsDomainModel.articles.map {
        ArticlesPresenterEntity(
          SourcePresenterEntity(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toMutableList() as ArrayList<ArticlesPresenterEntity>
    )
  }
}