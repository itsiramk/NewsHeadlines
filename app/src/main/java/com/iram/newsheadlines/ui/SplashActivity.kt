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
        checkIfUserHasSavedDetails()
    }
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