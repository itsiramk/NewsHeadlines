package com.iram.newsheadlines.network

import com.iram.newsheadlines.model.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface iService {

    @GET("top-headlines/sources")
    suspend fun getNewsTopHeadlines(@Query("apiKey") apiKey: String): Response<NewsResult>

}