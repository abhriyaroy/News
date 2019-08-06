package com.zebrostudio.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zebrostudio.news.data.database.dao.NewsDao
import com.zebrostudio.news.data.database.entity.NewsEntity

const val DATABASE_VERSION = 1

@TypeConverters(NewsDataEntityTypeConverter::class)
@Database(entities = [(NewsEntity::class)], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
  abstract fun newsDao(): NewsDao
}