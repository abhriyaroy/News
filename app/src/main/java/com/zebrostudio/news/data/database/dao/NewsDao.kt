package com.zebrostudio.news.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zebrostudio.news.data.database.entity.NewsEntity
import com.zebrostudio.news.data.database.entity.TABLE_NAME
import io.reactivex.Single

const val RETRIEVE_ALL_DATA_QUERY = "Select * from $TABLE_NAME"
const val DELETE_ALL_QUERY = "Delete from $TABLE_NAME"

@Dao
interface NewsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun setCache(newsEntity: NewsEntity): Long

  @Query(RETRIEVE_ALL_DATA_QUERY)
  fun getCache(): Single<List<NewsEntity>>

  @Query(DELETE_ALL_QUERY)
  fun deleteAllCache()

}