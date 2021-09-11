package com.iram.newsheadlines.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iram.newsheadlines.entity.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(movieList: List<News>)

    @Query("SELECT * FROM News")
    fun getNewsList(): LiveData<List<News>>
}