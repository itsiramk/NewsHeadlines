package com.iram.newsheadlinese.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iram.newsheadlines.db.dao.Converters
import com.iram.newsheadlines.db.dao.LoginDao
import com.iram.newsheadlines.db.dao.NewsDao
import com.iram.newsheadlines.entity.News
import com.iram.newsheadlines.db.entity.UserCredentials


@Database(entities = [News::class, UserCredentials::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun loginDao(): LoginDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "news_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
