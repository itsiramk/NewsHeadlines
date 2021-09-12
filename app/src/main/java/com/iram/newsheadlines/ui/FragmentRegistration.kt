package com.iram.newsheadlines.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iram.newsheadlines.R
import com.iram.newsheadlines.databinding.LayoutSignupBinding
import com.iram.newsheadlines.utils.autoCleared
import com.iram.newsheadlines.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class FragmentRegistration : Fragment(), TextWatcher {

    private val loginViewModel: LoginViewModel by viewModels()
    private var binding: LayoutSignupBinding by autoCleared()
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +  //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{4,}" +  //at least 4 characters
                "$"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvEmail.addTextChangedListener(this)
        binding.tvPwd.addTextChangedListener(this)
        binding.tvConfirmPwd.addTextChangedListener(this)
        binding.btnCreateAccount.setOnClickListener {
            val emailFlag:Boolean
            var pwdFlag:Boolean
            emailFlag = validateEmail()
            pwdFlag = validatePassword()
            if(emailFlag && pwdFlag)
                saveDataInDb()
        }
    }

    private fun saveDataInDb() {
        val loginEmail = binding.tvEmail.text.toString().trim()
        val loginPwd = binding.tvPwd.text.toString().trim()
        val loginConfirmPwd = binding.tvConfirmPwd.text.toString().trim()
        if (loginPwd == loginConfirmPwd) {
            loginViewModel.saveToDataStore(loginEmail, loginPwd)
            findNavController().navigate(R.id.action_fragmentRegistration_to_fragmentLogin)
        } else {
            binding.tilPassword.error = getString(R.string.password_mismatch)
            binding.tilConfirmPassword.error = getString(R.string.password_mismatch)
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = binding.tvEmail.text.toString().trim()
        return if (emailInput.isEmpty()) {
            binding.tilEmail.error = getString(R.string.empty_field_error)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            binding.tilEmail.error = getString(R.string.valid_email_error)
            false
        } else {
            binding.tilEmail.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput: String = binding.tvPwd.text.toString().trim()
        val confirmpasswordInput: String = binding.tvConfirmPwd.text.toString().trim()
        return if (passwordInput.isEmpty() && confirmpasswordInput.isEmpty()) {
            binding.tilPassword.error = getString(R.string.empty_field_error)
            binding.tilConfirmPassword.error = getString(R.string.empty_field_error)
            false
        }else if (passwordInput.isEmpty()) {
            binding.tilPassword.error = getString(R.string.empty_field_error)
            false
        } else if (confirmpasswordInput.isEmpty()) {
            binding.tilConfirmPassword.error = getString(R.string.empty_field_error)
            false
        }  else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.tilPassword.error = getString(R.string.password_weak_error)
            false
        }  else if (!PASSWORD_PATTERN.matcher(confirmpasswordInput).matches()) {
            binding.tilConfirmPassword.error = getString(R.string.password_weak_error)
            false
        } else {
            binding.tilPassword.error = null
            binding.tilConfirmPassword.error = null
            true
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        binding.tilEmail.error = ""
        binding.tilConfirmPassword.error = ""
        binding.tilPassword.error = ""
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
    }

    override fun afterTextChanged(s: Editable?) {

        if (binding.tvPwd.text.hashCode() == s.hashCode())
        {
            if (!PASSWORD_PATTERN.matcher(binding.tvPwd.text.toString().trim()).matches()) {
                binding.tilPassword.error = getString(R.string.password_weak_error)
            }
        }
        else if (binding.tvConfirmPwd.text.hashCode() == s.hashCode())
        {
            if (!PASSWORD_PATTERN.matcher(binding.tvConfirmPwd.text.toString().trim()).matches()) {
                binding.tvConfirmPwd.error = getString(R.string.password_weak_error)
            }
        }

        if (!binding.tvConfirmPwd.text.toString().trim().equals(binding.tvPwd.text.toString().trim())) {
                binding.tilConfirmPassword.error = getString(R.string.password_mismatch)
            }
        }
    }