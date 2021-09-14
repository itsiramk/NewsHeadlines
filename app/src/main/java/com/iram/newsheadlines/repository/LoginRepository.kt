package com.iram.newsheadlines.repository

import com.iram.newsheadlines.db.dao.LoginDao
import com.iram.newsheadlines.db.entity.UserCredentials
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDao: LoginDao
) {

    suspend fun createUserRecords(user: UserCredentials): Long {
        return loginDao.insertToRoomDatabase(user)
    }

    val getUserDetails: Flow<List<UserCredentials>> get() = loginDao.getUserDetails()

    suspend fun deleteSingleUserRecord() {
        loginDao.deleteSingleUserDetails(1)
    }
}