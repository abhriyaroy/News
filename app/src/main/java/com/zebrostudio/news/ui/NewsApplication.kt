package com.zebrostudio.news.ui

import android.app.Activity
import android.app.Application
import com.zebrostudio.news.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector {

  @Inject
  internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerAppComponent.builder()
      .application(this)
      .build()
      .inject(this)
  }

  override fun activityInjector() = activityDispatchingAndroidInjector

}