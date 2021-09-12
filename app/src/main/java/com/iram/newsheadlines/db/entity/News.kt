package com.iram.newsheadlines.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "news")
data class News(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val newsID:Int,
    val id: String? = null,
    val country: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val category: String? = null,
    val url: String? = null

)