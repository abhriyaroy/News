package com.zebrostudio.news.di

import androidx.lifecycle.ViewModelProvider
import com.zebrostudio.news.di.scopes.PerApplication
import com.zebrostudio.news.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilder {

  @PerApplication
  @Binds
  abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}