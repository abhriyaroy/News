package com.zebrostudio.news.di

import com.zebrostudio.news.di.scopes.PerActivity
import com.zebrostudio.news.ui.adapter.NewsListAdapterModule
import com.zebrostudio.news.ui.details.DetailsActivity
import com.zebrostudio.news.ui.details.DetailsModule
import com.zebrostudio.news.ui.main.MainActivity
import com.zebrostudio.news.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @PerActivity
  @ContributesAndroidInjector(modules = [(MainModule::class), (NewsListAdapterModule::class)])
  abstract fun contributesMainActivityInjector(): MainActivity

  @PerActivity
  @ContributesAndroidInjector(modules = [(DetailsModule::class)])
  abstract fun contributesDetailActivityInejector(): DetailsActivity

}
