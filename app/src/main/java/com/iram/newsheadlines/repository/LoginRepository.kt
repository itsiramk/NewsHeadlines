package com.iram.newsheadlines.repository

import androidx.lifecycle.LiveData
import com.iram.newsheadlines.db.dao.LoginDao
import com.iram.newsheadlines.db.dao.NewsDao
import com.iram.newsheadlines.entity.News
import com.iram.newsheadlines.db.entity.UserCredentials
import com.iram.newsheadlines.remote.ServerDataSource
import com.iram.newsheadlines.remote.fetchData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class LoginRepository @Inject constructor(
    private val loginDao: LoginDao
) {

    suspend fun createUserRecords(user: UserCredentials) : Long {
        return loginDao.insertToRoomDatabase(user)
    }

    val getUserDetails: Flow<List<UserCredentials>> get() =  loginDao.getUserDetails()

}