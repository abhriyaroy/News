package com.zebrostudio.news.presenter

interface BasePresenter<T> {
  fun attachView(view: T)
  fun detachView()
}