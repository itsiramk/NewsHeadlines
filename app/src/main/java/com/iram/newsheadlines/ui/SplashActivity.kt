package com.iram.newsheadlines.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.iram.newsheadlines.databinding.ActivitySplashBinding
import com.iram.newsheadlines.db.entity.UserCredentials
import com.iram.newsheadlines.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var  binding: ActivitySplashBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        val user = MutableLiveData<String>()
        val pd = MutableLiveData<String>()

        loginViewModel.getUserName.asLiveData().observe(this) {
            user.postValue(it)
        }
        loginViewModel.getUserPwd.asLiveData().observe(this) {
            pd.postValue(it)
        }

        loginViewModel.getUserDetails()
        loginViewModel.loginDetails.observe(this){
            initViews(it.emailID,it.password)
        }*/
      //  binding.pBar.visibility = View.VISIBLE
        checkIfUserHasSavedDetails()
    }
/*
    fun doGetUserDetails(){

        lateinit var userCredentials : UserCredentials
        lifecycleScope.launch(Dispatchers.IO) {
            loginViewModel.getUserDetails()
            loginViewModel.userEmail.collect {
                userCredentials.emailID = it
            }
            loginViewModel.userPwd.collect {
                userCredentials.password = it
            }
            initViews(userCredentials.emailID,userCredentials.password)

        }
    }
*/
    private fun checkIfUserHasSavedDetails(){

        loginViewModel.savedKey.observe(this){
            if (it){
                startNewsActivity()
            } else{
                startLoginActivity()
            }

        }
    }

    private fun startLoginActivity() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startNewsActivity() {
        finish()
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
    }
}