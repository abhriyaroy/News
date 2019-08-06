package com.zebrostudio.news.data.mapper

import com.zebrostudio.news.data.model.NewsDataEntity
import com.zebrostudio.news.domain.model.ArticlesDomainModel
import com.zebrostudio.news.domain.model.NewsDomainModel
import com.zebrostudio.news.domain.model.SourceDomainModel

interface NewsDataEntityMapper {
  fun mapFromDataEntity(newsDataEntity: NewsDataEntity): NewsDomainModel
}

class NewsDataEntityMapperImpl : NewsDataEntityMapper {

  override fun mapFromDataEntity(newsDataEntity: NewsDataEntity): NewsDomainModel {
    return NewsDomainModel(
      newsDataEntity.status,
      newsDataEntity.totalResults,
      newsDataEntity.articles.map {
        ArticlesDomainModel(
          SourceDomainModel(it.source.id, it.source.name),
          it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content
        )
      }.toMutableList() as ArrayList<ArticlesDomainModel>
    )
  }
}