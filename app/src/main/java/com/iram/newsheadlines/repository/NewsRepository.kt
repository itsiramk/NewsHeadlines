package com.iram.newsheadlines.repository

import com.iram.newsheadlines.BuildConfig
import com.iram.newsheadlines.db.dao.NewsDao
import com.iram.newsheadlines.model.NewsResult
import com.iram.newsheadlines.remote.ServerDataSource
import com.iram.newsheadlines.remote.fetchData
import com.iram.newsheadlines.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private var serverDataSource: ServerDataSource
) {

    fun getMoviesData() = fetchData(
        databaseQuery = { newsDao.getNewsList() },
        networkCall = { serverDataSource.getNewsList() },
        saveCallResult = { it.sources?.let { it1 -> newsDao.insertDetails(it1) } }
    )

    suspend fun getNewsHeadlines(): Resource<NewsResult> {
        return serverDataSource.getNewsList()
    }

}