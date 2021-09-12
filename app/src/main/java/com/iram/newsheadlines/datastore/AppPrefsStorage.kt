package com.iram.newsheadlines.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AppPrefsStorage @Inject constructor(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "user_prefs"
    )

    companion object {
        val USER_PWD_KEY = preferencesKey<String>("USER_PWD")
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
    }

    suspend fun storeUser(name: String,pwd:String) {
        dataStore.edit {
            it[USER_PWD_KEY] = pwd
            it[USER_NAME_KEY] = name
        }
    }

    val userPwdFlow: Flow<String> = dataStore.data.map {
        it[USER_PWD_KEY] ?: ""
    }

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }
}
