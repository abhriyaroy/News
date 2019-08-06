package com.zebrostudio.news.data.api

import com.zebrostudio.news.data.model.NewsDataEntity
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApiClient {
  @GET("top-headlines?country=in&apiKey=a103f584df8848ffbcbeee024e4434f1")
  fun getNews(): Single<NewsDataEntity>
}