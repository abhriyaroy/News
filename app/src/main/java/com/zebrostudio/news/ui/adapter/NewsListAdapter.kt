package com.zebrostudio.news.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.zebrostudio.news.R
import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListPresenter
import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListView
import com.zebrostudio.news.presenter.model.ArticlesPresenterEntity
import com.zebrostudio.news.ui.details.DetailsActivity
import com.zebrostudio.news.ui.imageloader.ImageLoader
import kotlinx.android.synthetic.main.item_news_list.view.*

const val NEWS_HEADING = "news_heading"
const val NEWS_URL = "news_url"

class NewsListAdapter(
  private val context: Context,
  private val imageLoader: ImageLoader,
  private val newsListPresenter: NewsListPresenter
) : Adapter<NewsListViewHolder>() {

  private var articlesList = listOf<ArticlesPresenterEntity>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
    return NewsListViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.item_news_list,
        parent,
        false
      ), context, imageLoader, newsListPresenter
    )
  }

  override fun getItemCount(): Int {
    return newsListPresenter.getItemCount(articlesList)
  }

  override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
    newsListPresenter.onBindRepositoryRowViewAtPosition(holder, articlesList, position)
  }

  fun setList(list: List<ArticlesPresenterEntity>) {
    articlesList = list
    notifyDataSetChanged()
  }

}

class NewsListViewHolder(
  itemView: View,
  private val context: Context,
  private val imageLoader: ImageLoader,
  private val newsListPresenter: NewsListPresenter
) : RecyclerView.ViewHolder(itemView), NewsListView {
  override fun showHeadline(headLine: String?) {
    itemView.headline.text = headLine
  }

  override fun showDescription(description: String?) {
    itemView.description.text = description
  }

  override fun showImage(imagePath: String?) {
    imageLoader.loadImage(itemView.imageView, imagePath)
  }

  override fun attachClickListener() {
    itemView.setOnClickListener {
      newsListPresenter.handleItemClick(this, adapterPosition)
    }
  }

  override fun showNewsDetails(headLine: String?, url: String?) {
    with(Intent(context, DetailsActivity::class.java).apply {
      putExtra(NEWS_HEADING, headLine)
      putExtra(NEWS_URL, url)
    }) {
      context.startActivity(this)
    }
  }

}