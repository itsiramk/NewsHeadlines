package com.iram.newsheadlines.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.iram.newsheadlines.entity.News
import com.iram.newsheadlines.repository.NewsRepository
import com.iram.newsheadlines.utils.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel @ViewModelInject constructor(
    private val newsRepo: NewsRepository) : ViewModel() {
    val newsListLiveData = newsRepo.getNewsData()

    fun getNewsListByTitle(title:String): LiveData<News> {
        return newsRepo.getNewsListByTitle(title)
    }

}
