package com.zebrostudio.news.presenter.model

import com.zebrostudio.news.presenter.model.ArticlesPresenterEntity

data class NewsPresenterEntity(
  val status: String?,
  val totalResults: Int?,
  val articles: ArrayList<ArticlesPresenterEntity>
)