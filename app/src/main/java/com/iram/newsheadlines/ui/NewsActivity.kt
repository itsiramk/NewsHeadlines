package com.iram.newsheadlines.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.iram.newsheadlines.R
import com.iram.newsheadlines.databinding.ActivityNewsBinding
import com.iram.newsheadlines.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                confirmLogoutAlert()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmLogoutAlert() {
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton(
                "Yes"
            ) { dialog, which -> deleteUserLoginData() }
            .setNegativeButton("No", null)
            .show()

    }

    private fun deleteUserLoginData() {
        loginViewModel.doDeleteSingleUserRecord()
        loginViewModel.setSavedKey(false)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}