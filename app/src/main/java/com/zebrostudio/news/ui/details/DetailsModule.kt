package com.zebrostudio.news.ui.details

import com.zebrostudio.news.di.scopes.PerActivity
import com.zebrostudio.news.presenter.details.DetailsContract.DetailsPresenter
import com.zebrostudio.news.presenter.details.DetailsPresenterImpl
import com.zebrostudio.news.ui.utils.ResourceUtils
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {

  @PerActivity
  @Provides
  fun providesDetailsPresenter(resourceUtils: ResourceUtils)
      : DetailsPresenter = DetailsPresenterImpl(resourceUtils)
}