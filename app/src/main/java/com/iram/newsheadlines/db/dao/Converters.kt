package com.iram.newsheadlines.db.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.iram.newsheadlines.model.Sources


class Converters {
    @TypeConverter
    fun fromString(value: String): Sources {
        return Gson().fromJson(value, Sources::class.java)
    }

    @TypeConverter
    fun fromSources(source: Sources): String? {
        return Gson().toJson(source)
    }
}