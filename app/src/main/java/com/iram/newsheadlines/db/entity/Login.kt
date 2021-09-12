package com.iram.newsheadlines.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loginDetails")
data class Login(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int=0,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "password")
    var password: String? = null
)