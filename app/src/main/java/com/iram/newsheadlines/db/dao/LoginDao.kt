package com.iram.newsheadlines.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.iram.newsheadlines.entity.News
import com.iram.newsheadlines.db.entity.UserCredentials
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToRoomDatabase(user: UserCredentials) : Long

    @Transaction
    @Query("SELECT * FROM loginDetails ORDER BY id DESC")
    fun getUserDetails() : Flow<List<UserCredentials>>

    //get single user inserted to room database
    @Transaction
    @Query("SELECT * FROM loginDetails WHERE id = :id ORDER BY id DESC")
    fun getSingleUserDetails(id: Long) : Flow<UserCredentials>
}