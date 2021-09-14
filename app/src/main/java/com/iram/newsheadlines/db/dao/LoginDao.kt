package com.iram.newsheadlines.db.dao

import androidx.room.*
import com.iram.newsheadlines.db.entity.UserCredentials
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToRoomDatabase(user: UserCredentials): Long

    @Transaction
    @Query("SELECT * FROM loginDetails ORDER BY id DESC")
    fun getUserDetails(): Flow<List<UserCredentials>>

    @Query("DELETE FROM loginDetails WHERE id = :id")
    suspend fun deleteSingleUserDetails(id: Int)
}