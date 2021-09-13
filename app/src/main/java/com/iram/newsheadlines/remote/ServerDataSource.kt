package com.iram.newsheadlines.remote

import com.iram.newsheadlines.BuildConfig
import com.iram.newsheadlines.network.iService
import javax.inject.Inject

class ServerDataSource @Inject constructor(
    private val iService: iService
) : BaseDataSource() {

    suspend fun getNewsList() = getResult { iService.getNewsTopHeadlines("in",BuildConfig.NEWS_API_KEY) }
}
