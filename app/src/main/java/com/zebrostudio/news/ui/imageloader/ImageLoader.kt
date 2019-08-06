package com.zebrostudio.news.ui.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

interface ImageLoader {
  fun loadImage(imageView: ImageView, imageUrl: String?)
}

class ImageLoaderImpl(private val context: Context) : ImageLoader {
  override fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(context)
      .load(imageUrl)
      .transition(withCrossFade())
      .apply(RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
      .into(imageView)
  }
}