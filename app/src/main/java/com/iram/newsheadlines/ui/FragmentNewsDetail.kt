package com.iram.newsheadlines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iram.newsheadlines.databinding.LayoutLoginBinding
import com.iram.newsheadlines.databinding.LayoutNewsdetailsBinding
import com.iram.newsheadlines.utils.autoCleared
import com.iram.newsheadlines.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentNewsDetail: Fragment() {
    private var binding:LayoutNewsdetailsBinding by autoCleared()
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutNewsdetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDataFromIntent()
    }

    private fun fetchDataFromIntent() {
        arguments?.getString("title")?.let { fetchDataFromDb(it.toString()) }
    }

    fun fetchDataFromDb(title: String) {
        newsViewModel.getNewsListByTitle(title).observe(viewLifecycleOwner,
            { news ->
                binding.textView.text = news.content
            })
    }
}