package com.zebrostudio.news.di

import android.app.Application
import com.zebrostudio.news.di.scopes.PerApplication
import com.zebrostudio.news.ui.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(
  modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class), (ViewModelBuilder::class)]
)
interface AppComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: NewsApplication)

}