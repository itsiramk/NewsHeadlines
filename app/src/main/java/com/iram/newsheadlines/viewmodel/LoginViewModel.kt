package com.iram.newsheadlines.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.iram.newsheadlines.datastore.AppPrefsStorage
import com.iram.newsheadlines.db.entity.UserCredentials
import com.iram.newsheadlines.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginViewModel @ViewModelInject constructor(
    private val appPrefsStorage: AppPrefsStorage, private val loginRepository: LoginRepository
    ) : ViewModel() {

    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    fun insertUserDetails(user: UserCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(loginRepository.createUserRecords(user))
        }
    }
    private val _userDetails = MutableStateFlow<List<UserCredentials>>(emptyList())
    val userDetails : StateFlow<List<UserCredentials>> =  _userDetails

    fun doGetUserDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.getUserDetails
                .collect {
                    _userDetails.value = it
                }
        }
    }
    val savedKey = appPrefsStorage.savedKey.asLiveData()
    fun setSavedKey(key: Boolean) {
        viewModelScope.launch {
            appPrefsStorage.setSavedKey(key)
        }
    }
    fun doDeleteSingleUserRecord(){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.deleteSingleUserRecord()
        }
    }

}
