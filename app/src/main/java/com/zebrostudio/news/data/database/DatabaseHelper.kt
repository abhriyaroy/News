package com.zebrostudio.news.data.database

import android.content.Context
import androidx.room.Room
import com.zebrostudio.news.data.database.dao.NewsDao
import com.zebrostudio.news.data.database.entity.NewsEntity
import com.zebrostudio.news.data.model.NewsDataEntity
import com.zebrostudio.news.exceptions.CacheAbsentException
import io.reactivex.Single

interface DatabaseHelper {
  fun saveToCache(newsDataEntity: NewsDataEntity): Single<Long>
  fun getCachedNews(): Single<NewsDataEntity>
  fun clearCache()
}

private const val DATABASE_NAME = "AppDatabase.db"
private const val AUTO_INCREMENT_KEY = 0L
private const val DEFAULT_LOCATION = "INDIA"

class DatabaseHelperImpl(private val context: Context) : DatabaseHelper {

  private var databaseInstance: AppDatabase? = null

  override fun saveToCache(newsDataEntity: NewsDataEntity): Single<Long> {
    return Single.just(
      getNewsDao().setCache(
        NewsEntity(
          AUTO_INCREMENT_KEY,
          System.currentTimeMillis(),
          DEFAULT_LOCATION,
          newsDataEntity
        )
      )
    )
  }

  override fun getCachedNews(): Single<NewsDataEntity> {
    return getNewsDao().getCache()
      .flatMap {
        if (it.isEmpty()) {
          Single.error(CacheAbsentException())
        } else {
          Single.just(it[0].data)
        }
      }
  }

  override fun clearCache() {
    getNewsDao().deleteAllCache()
  }

  private fun getNewsDao(): NewsDao {
    if (databaseInstance == null) {
      databaseInstance =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }
    return databaseInstance!!.newsDao()
  }
}