package com.iram.newsheadlines.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject


class AppPrefsStorage @Inject constructor(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "user_prefs"
    )

    companion object {
        val SAVED_KEY = preferencesKey<Boolean>("saved_key")
    }

    suspend fun setSavedKey(boolean: Boolean) {
        dataStore.edit {
            it[SAVED_KEY] = boolean
        }
    }

    val savedKey: Flow<Boolean> = dataStore.data.map {
        it[SAVED_KEY] ?: false
    }
}
