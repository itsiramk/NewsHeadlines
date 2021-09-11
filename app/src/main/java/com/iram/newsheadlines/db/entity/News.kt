package com.iram.newsheadlines.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "news")
data class News(

    @PrimaryKey
    @SerializedName("emailId")
    @Expose
    val userEmail: String,

    val country: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val id: String? = null,
    val category: String? = null,
    val url: String? = null

)