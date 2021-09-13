package com.iram.newsheadlines.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loginDetails")
data class UserCredentials (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var emailID: String?=null,
    var password: String?=null,
)