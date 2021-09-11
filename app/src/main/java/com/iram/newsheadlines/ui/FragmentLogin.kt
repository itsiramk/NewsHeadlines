package com.iram.newsheadlines.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.iram.newsheadlines.R

class FragmentLogin : Fragment() {

    private lateinit var tvSignUp: TextView;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_login, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSignUp = view.findViewById(R.id.tvSignUp)
        tvSignUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentLogin_to_fragmentRegistration)
        }
    }
}