package com.iram.newsheadlines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.newsheadlines.R
import com.iram.newsheadlines.adapters.NewsListAdapter
import com.iram.newsheadlines.databinding.LayoutNewslistBinding
import com.iram.newsheadlines.utils.Resource
import com.iram.newsheadlines.utils.autoCleared
import com.iram.newsheadlines.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentNewsList : Fragment(), NewsListAdapter.NewsItemListener {

    private var binding: LayoutNewslistBinding by autoCleared()
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutNewslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchNewsData()
    }

    private fun initViews() {
        newsAdapter = NewsListAdapter(this)
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.itemAnimator = DefaultItemAnimator()
        binding.rvNews.adapter = newsAdapter
    }

    private fun fetchNewsData() {
        newsViewModel.newsListLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        newsAdapter.setItems(ArrayList(it.data))
                    }
                }
                Resource.Status.ERROR -> {
                    binding.tvNoData.visibility = View.VISIBLE
                }
                Resource.Status.LOADING ->
                    binding.pBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedItemData(title: String) {
        findNavController().navigate(
            R.id.action_fragmentNewsList_to_fragmentNewsDetail,
            bundleOf("title" to title)
        )
    }
}