package com.iram.newsheadlines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iram.newsheadlines.databinding.ActivityMainBinding
import com.iram.newsheadlines.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}