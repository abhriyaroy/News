package com.zebrostudio.news.domain.model

data class ArticlesDomainModel(
  val source: SourceDomainModel,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String?,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?
)