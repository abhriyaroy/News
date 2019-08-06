package com.zebrostudio.news.domain.main

import com.zebrostudio.news.data.Repository
import com.zebrostudio.news.domain.model.NewsDomainModel
import io.reactivex.Single

interface NewsUseCase {
  fun getNews(): Single<NewsDomainModel>
}

class NewsInteractor(private val repository: Repository) : NewsUseCase {
  override fun getNews(): Single<NewsDomainModel> {
    return repository.getNews()
  }
}