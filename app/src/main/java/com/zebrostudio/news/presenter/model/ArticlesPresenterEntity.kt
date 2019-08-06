package com.zebrostudio.news.presenter.model

data class ArticlesPresenterEntity(
  val source: SourcePresenterEntity,
  val author: String?,
  val title: String?,
  val description: String?,
  val url: String?,
  val urlToImage: String?,
  val publishedAt: String?,
  val content: String?
)