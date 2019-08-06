package com.zebrostudio.news.domain.model

import com.zebrostudio.news.domain.model.ArticlesDomainModel

data class NewsDomainModel(
  val status: String?,
  val totalResults: Int?,
  val articles: ArrayList<ArticlesDomainModel>
)