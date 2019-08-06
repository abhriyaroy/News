package com.zebrostudio.news.ui.adapter

import com.zebrostudio.news.di.scopes.PerActivity
import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListPresenter
import com.zebrostudio.news.presenter.adapter.NewsListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class NewsListAdapterModule {

  @PerActivity
  @Provides
  fun providesNewsListPresenter(): NewsListPresenter = NewsListPresenterImpl()
}