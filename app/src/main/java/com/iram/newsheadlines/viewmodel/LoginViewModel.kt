package com.iram.newsheadlines.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iram.newsheadlines.datastore.AppPrefsStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(private val appPrefsStorage: AppPrefsStorage)
    : ViewModel() {

    val getUserName = appPrefsStorage.userNameFlow
    val getUserPwd = appPrefsStorage.userPwdFlow

     fun saveToDataStore(email: String,pwd:String) {
        viewModelScope.launch(Dispatchers.IO) {
            appPrefsStorage.storeUser(email,pwd)
        }
    }
}