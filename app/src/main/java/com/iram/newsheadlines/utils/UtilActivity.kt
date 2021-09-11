package com.iram.newsheadlines.utils

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.iram.newsheadlines.R

class UtilActivity {
    companion object {
        fun showProgress(pBar: ProgressBar, status: Boolean) {
            if (status) {
                pBar.visibility = View.VISIBLE
            } else {
                pBar.visibility = View.GONE
            }
        }

        fun loadImage(context: CoordinatorLayout, imageView: AppCompatImageView, path: String) {
            Glide.with(context).load(path)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
        }
    }
}