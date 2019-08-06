package com.zebrostudio.news.data.database

import androidx.room.TypeConverter
import com.zebrostudio.news.data.model.NewsDataEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object NewsDataEntityTypeConverter {
  private val gson = Gson()

  @TypeConverter
  @JvmStatic
  fun stringToArticleList(string: String): NewsDataEntity {
    return gson.fromJson(string, object : TypeToken<NewsDataEntity>() {}.type)
  }

  @TypeConverter
  @JvmStatic
  fun articleListToString(newsDataEntity: NewsDataEntity): String {
    return gson.toJson(newsDataEntity, object : TypeToken<NewsDataEntity>() {}.type)
  }
}