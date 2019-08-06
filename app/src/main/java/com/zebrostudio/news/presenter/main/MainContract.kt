package com.zebrostudio.news.presenter.main

import com.zebrostudio.news.presenter.BasePresenter
import com.zebrostudio.news.presenter.BaseView
import com.zebrostudio.news.presenter.model.NewsPresenterEntity

interface MainContract {

  interface MainView : BaseView {
    fun showProgressLoader()
    fun hideProgressLoader()
    fun setNewsList(newsPresenterEntity: NewsPresenterEntity)
    fun showNewsList()
    fun hideNewsList()
    fun showErrorMessage()
  }

  interface MainPresenter : BasePresenter<MainView> {
    fun decorateView()
    fun handleRefreshClick()
  }
}