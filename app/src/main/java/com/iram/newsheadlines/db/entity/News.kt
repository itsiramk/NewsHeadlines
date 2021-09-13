package com.iram.newsheadlines.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.iram.newsheadlines.model.Sources


@Entity(tableName = "news")
data class News(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val newsID: Int,
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val title: String? = null,
    val content: String? = null,
    val url: String? = null,
    val source:Sources?=null

)