package com.iram.newsheadlines.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.iram.newsheadlines.databinding.ActivitySplashBinding
import com.iram.newsheadlines.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var userName: String = ""
        var userPwd: String = ""
        loginViewModel.getUserName.asLiveData().observe(this) {
            userName = it.toString()
        }
        loginViewModel.getUserPwd.asLiveData().observe(this) {
            userPwd = it.toString()
        }
        if (userName.isNotEmpty() && userPwd.isNotEmpty()) {
            startNewsActivity()
        }else{
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun startNewsActivity() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }
}