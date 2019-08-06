package com.zebrostudio.news.presenter.main

import com.zebrostudio.news.domain.main.NewsUseCase
import com.zebrostudio.news.presenter.main.MainContract.MainPresenter
import com.zebrostudio.news.presenter.main.MainContract.MainView
import com.zebrostudio.news.presenter.mapper.NewsPresenterEntityMapper
import com.zebrostudio.news.ui.MainScheduler
import com.uber.autodispose.AutoDispose.autoDisposable

class MainPresenterImpl(
  private val newsUseCase: NewsUseCase,
  private val newsPresenterEntityMapper: NewsPresenterEntityMapper,
  private val mainScheduler: MainScheduler
) : MainPresenter {

  private var mainView: MainView? = null

  override fun attachView(view: MainView) {
    mainView = view
  }

  override fun detachView() {
    mainView = null
  }

  override fun decorateView() {
    showLatestNews()
  }

  override fun handleRefreshClick() {
    showLatestNews()
  }

  private fun showLatestNews() {
    newsUseCase.getNews()
      .map {
        newsPresenterEntityMapper.mapToPresenterEntity(it)
      }
      .observeOn(mainScheduler.getMainScheduler())
      .doOnSubscribe {
        mainView?.hideNewsList()
        mainView?.showProgressLoader()
      }
      .`as`(autoDisposable(mainView!!.getScope()))
      .subscribe({
        mainView?.hideProgressLoader()
        mainView?.showNewsList()
        mainView?.setNewsList(it)
      }, {
        mainView?.showErrorMessage()
        mainView?.hideProgressLoader()
      })
  }
}