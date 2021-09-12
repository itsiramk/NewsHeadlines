package com.iram.newsheadlines.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import com.iram.newsheadlines.R
import com.iram.newsheadlines.databinding.LayoutLoginBinding
import com.iram.newsheadlines.utils.autoCleared
import com.iram.newsheadlines.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentLogin : Fragment(), TextWatcher {

    private val loginViewModel: LoginViewModel by viewModels()
    private var binding: LayoutLoginBinding by autoCleared()
    private var userName: String = ""
    private var userPwd: String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_fragmentLogin_to_fragmentRegistration)
        }
        initViews()
        binding.btnLogin.setOnClickListener {
            val flag = validateInputFields()
            if(!flag){
                showAlert(getString(R.string.login_failed))
            }
        }
    }

    private fun initViews() {
        binding.pBar.visibility = View.VISIBLE
        binding.tvEmail.addTextChangedListener(this)
        binding.tvPwd.addTextChangedListener(this)
        loginViewModel.getUserName.asLiveData().observe(viewLifecycleOwner) {
            userName = it.toString()
        }
        loginViewModel.getUserPwd.asLiveData().observe(viewLifecycleOwner) {
            userPwd = it.toString()
        }
        binding.pBar.visibility = View.GONE
    }

    private fun startNewsActivity() {
        val intent = Intent(context, NewsActivity::class.java)
        startActivity(intent)
    }

    private fun validateInputFields():Boolean {
        var email = binding.tvEmail.text?.trim().toString()
        var pwd = binding.tvPwd.text?.trim().toString()
        if (email.isEmpty()) {
            binding.tilUsername.error = getString(R.string.enter_email)
            return false
        }
        if (pwd.isEmpty()) {
            binding.tilPassword.error = getString(R.string.enter_pwd)
            return false
        }
        if (userName.isNotEmpty() || userPwd.isNotEmpty()) {
            binding.pBar.visibility = View.GONE

            if (userName == email && userPwd == pwd) {
                startNewsActivity()
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        binding.tilUsername.error = null
        binding.tilPassword.error = null
    }

    override fun afterTextChanged(s: Editable?) {
        validateInputFields()
    }

    fun showAlert(msg:String){
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Alert!!")
        alertDialog.setMessage(msg)
        alertDialog.setPositiveButton("OK") { dialog, which ->
            dialog.cancel()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.show()
    }
}