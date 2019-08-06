package com.zebrostudio.news.ui.main

import android.content.Context
import com.zebrostudio.news.di.scopes.PerActivity
import com.zebrostudio.news.domain.main.NewsUseCase
import com.zebrostudio.news.presenter.main.MainContract.MainPresenter
import com.zebrostudio.news.presenter.main.MainPresenterImpl
import com.zebrostudio.news.presenter.mapper.NewsPresenterEntityMapper
import com.zebrostudio.news.presenter.mapper.NewsPresenterEntityMapperImpl
import com.zebrostudio.news.ui.MainScheduler
import com.zebrostudio.news.ui.imageloader.ImageLoader
import com.zebrostudio.news.ui.imageloader.ImageLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

  @PerActivity
  @Provides
  fun providesMainPresenter(
    newsUseCase: NewsUseCase,
    newsPresenterEntityMapper: NewsPresenterEntityMapper,
    mainScheduler: MainScheduler
  ): MainPresenter =
    MainPresenterImpl(newsUseCase, newsPresenterEntityMapper, mainScheduler)

  @PerActivity
  @Provides
  fun providesNewsPresenterEntityMapper(): NewsPresenterEntityMapper =
    NewsPresenterEntityMapperImpl()

  @PerActivity
  @Provides
  fun providesImageLoader(context: Context): ImageLoader = ImageLoaderImpl(context)
}