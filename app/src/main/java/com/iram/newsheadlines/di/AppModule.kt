package com.iram.newsheadlines.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iram.newsheadlines.BuildConfig
import com.iram.newsheadlines.datastore.AppPrefsStorage
import com.iram.newsheadlines.db.dao.LoginDao
import com.iram.newsheadlines.db.dao.NewsDao
import com.iram.newsheadlines.network.iService
import com.iram.newsheadlines.remote.ServerDataSource
import com.iram.newsheadlines.repository.LoginRepository
import com.iram.newsheadlines.repository.NewsRepository
import com.iram.newsheadlines.viewmodel.LoginViewModel
import com.iram.newsheadlinese.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(350, TimeUnit.SECONDS)
            .readTimeout(315, TimeUnit.SECONDS)
            .writeTimeout(315, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.NEWS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideService(retrofit: Retrofit): iService = retrofit.create(iService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewsDao(db: AppDatabase) = db.newsDao()

    @Singleton
    @Provides
    fun provideLoginDao(db: AppDatabase) = db.loginDao()

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideRepository(
        serverDataSource: ServerDataSource,
        localDataSource: NewsDao
    ) = NewsRepository(localDataSource, serverDataSource)

    @Singleton
    @Provides
    fun provideLoginRepository(loginDao: LoginDao
    ) = LoginRepository(loginDao)

    @Singleton
    @Provides
    fun providesPreferenceStorage(
        appPreferenceStorage: AppPrefsStorage,loginRepository: LoginRepository)=
        LoginViewModel(appPreferenceStorage,loginRepository)
}