package com.zebrostudio.news.presenter.adapter

import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListPresenter
import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListView
import com.zebrostudio.news.presenter.model.ArticlesPresenterEntity

class NewsListPresenterImpl : NewsListPresenter {

  private var list = listOf<ArticlesPresenterEntity>()

  override fun getItemCount(list: List<ArticlesPresenterEntity>): Int {
    this.list = list
    return this.list.size
  }

  override fun onBindRepositoryRowViewAtPosition(
    newsListView: NewsListView,
    articlesList: List<ArticlesPresenterEntity>,
    position: Int
  ) {
    with(articlesList[position]) {
      newsListView.showHeadline(title)
      newsListView.showDescription(description)
      newsListView.showImage(urlToImage)
    }
    newsListView.attachClickListener()
  }

  override fun handleItemClick(newsListView: NewsListView, position: Int) {
    with(list[position]) {
      newsListView.showNewsDetails(title, url)
    }
  }

}
