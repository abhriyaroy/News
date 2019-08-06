package com.zebrostudio.news.data.model

import com.zebrostudio.news.data.model.ArticlesDataEntity

data class NewsDataEntity(
  val status: String?,
  val totalResults: Int?,
  val articles: ArrayList<ArticlesDataEntity>
)