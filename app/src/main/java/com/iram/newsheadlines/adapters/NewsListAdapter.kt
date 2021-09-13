package com.iram.newsheadlines.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.iram.newsheadlines.R
import com.iram.newsheadlines.databinding.ItemNewsBinding
import com.iram.newsheadlines.entity.News
import java.util.*

class NewsListAdapter(val listener: NewsItemListener) :
    RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    private var items = ArrayList<News>()

    fun setItems(items: ArrayList<News>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface NewsItemListener {
        fun onClickedItemData(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val itemDataBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListViewHolder(itemDataBinding, listener)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class NewsListViewHolder(
        private val itemBinding: ItemNewsBinding, private val listener: NewsItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var itemList: News

        fun bind(item: News) {
            itemList = item
            // val url = BuildConfig.SMALL_IMAGE_URL + item.url
            itemBinding.tvNewsTitle.text = item.title
            itemBinding.tvNewsData.text = item.source?.name
            Glide.with(itemBinding.root).load(item.urlToImage).transform(CircleCrop())
                .placeholder(R.drawable.img_googlenews)
                .placeholder(R.drawable.img_googlenews)
                .error(R.drawable.img_googlenews)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemBinding.imgNews)

            itemBinding.root.setOnClickListener {
                listener.onClickedItemData(itemList.title!!)
            }
        }
    }
}