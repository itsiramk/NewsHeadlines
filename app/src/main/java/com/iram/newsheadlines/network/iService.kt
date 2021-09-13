package com.iram.newsheadlines.network

import com.iram.newsheadlines.model.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface iService {

    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(@Query("country")country:String,@Query("apiKey") apiKey: String): Response<NewsResult>

}