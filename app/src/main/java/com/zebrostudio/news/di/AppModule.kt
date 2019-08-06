package com.zebrostudio.news.di

import android.app.Application
import android.content.Context
import com.zebrostudio.news.domain.main.NewsInteractor
import com.zebrostudio.news.domain.main.NewsUseCase
import com.zebrostudio.news.ui.MainScheduler
import com.zebrostudio.news.ui.MainSchedulerImpl
import com.zebrostudio.news.ui.utils.ResourceUtils
import com.zebrostudio.news.ui.utils.ResourceUtilsImpl
import com.zebrostudio.news.data.BackgroundScheduler
import com.zebrostudio.news.data.BackgroundSchedulerImpl
import com.zebrostudio.news.data.Repository
import com.zebrostudio.news.data.RepositoryImpl
import com.zebrostudio.news.data.api.NewsApiClientFactory
import com.zebrostudio.news.data.api.NewsApiClientFactoryImpl
import com.zebrostudio.news.data.database.DatabaseHelper
import com.zebrostudio.news.data.database.DatabaseHelperImpl
import com.zebrostudio.news.data.mapper.NewsDataEntityMapper
import com.zebrostudio.news.data.mapper.NewsDataEntityMapperImpl
import com.zebrostudio.news.di.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

  @PerApplication
  @Provides
  fun providesContext(application: Application): Context = application

  @PerApplication
  @Provides
  fun providesNewsDataEntityMapper(): NewsDataEntityMapper = NewsDataEntityMapperImpl()

  @PerApplication
  @Provides
  fun providesNewsApiClientFactory(): NewsApiClientFactory = NewsApiClientFactoryImpl()

  @PerApplication
  @Provides
  fun providesBackgroundScheduler(): BackgroundScheduler = BackgroundSchedulerImpl()

  @PerApplication
  @Provides
  fun providesMainScheduler(): MainScheduler = MainSchedulerImpl()

  @PerApplication
  @Provides
  fun providesDatabaseHelper(context: Context): DatabaseHelper = DatabaseHelperImpl(context)

  @PerApplication
  @Provides
  fun providesRepository(
    apiClientFactory: NewsApiClientFactory,
    databaseHelper: DatabaseHelper,
    newsDataEntityMapper: NewsDataEntityMapper,
    backgroundScheduler: BackgroundScheduler
  ): Repository =
    RepositoryImpl(apiClientFactory, databaseHelper, newsDataEntityMapper, backgroundScheduler)

  @PerApplication
  @Provides
  fun providesNewsUseCase(repository: Repository): NewsUseCase = NewsInteractor(repository)

  @PerApplication
  @Provides
  fun providesResourceUtils(context: Context): ResourceUtils = ResourceUtilsImpl(context)
}