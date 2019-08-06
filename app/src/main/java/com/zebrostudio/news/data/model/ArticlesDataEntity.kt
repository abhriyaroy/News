package com.zebrostudio.news.data.model

data class ArticlesDataEntity(
  val source: SourceDataEntity,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String?,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?
)