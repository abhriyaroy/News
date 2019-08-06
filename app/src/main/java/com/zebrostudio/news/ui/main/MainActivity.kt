package com.zebrostudio.news.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uber.autodispose.ScopeProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.zebrostudio.news.R
import com.zebrostudio.news.presenter.adapter.NewsListContract.NewsListPresenter
import com.zebrostudio.news.presenter.main.MainContract.MainPresenter
import com.zebrostudio.news.presenter.main.MainContract.MainView
import com.zebrostudio.news.presenter.model.NewsPresenterEntity
import com.zebrostudio.news.ui.adapter.NewsListAdapter
import com.zebrostudio.news.ui.imageloader.ImageLoader
import com.zebrostudio.news.ui.utils.gone
import com.zebrostudio.news.ui.utils.showToast
import com.zebrostudio.news.ui.utils.stringRes
import com.zebrostudio.news.ui.utils.visible
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

  @Inject
  lateinit var presenter: MainPresenter
  @Inject
  lateinit var newListPresenter: NewsListPresenter
  @Inject
  lateinit var imageLoader: ImageLoader
  private lateinit var recyclerViewAdapter: NewsListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter.attachView(this)
    initToolbar()
    initRecyclerView()
    presenter.decorateView()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.refresh_menu, menu)
    return true
  }

  override fun onDestroy() {
    presenter.detachView()
    super.onDestroy()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.refreshNews -> {
        presenter.handleRefreshClick()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun getScope(): ScopeProvider =
    AndroidLifecycleScopeProvider.from(this, ON_DESTROY)

  override fun hideProgressLoader() {
    progressCircle.gone()
  }

  override fun showProgressLoader() {
    progressCircle.visible()
  }

  override fun setNewsList(newsPresenterEntity: NewsPresenterEntity) {
    recyclerViewAdapter.setList(newsPresenterEntity.articles)
    recyclerView.scheduleLayoutAnimation()
  }

  override fun showNewsList() {
    recyclerView.visible()
  }

  override fun hideNewsList() {
    recyclerView.gone()
  }

  override fun showErrorMessage() {
    showToast(stringRes(R.string.main_activity_news_loading_error_message))
  }

  private fun initToolbar() {
    toolbar.title = stringRes(R.string.main_activity_tootlbar_title)
    setSupportActionBar(toolbar)
  }

  private fun initRecyclerView() {
    recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    recyclerViewAdapter = NewsListAdapter(this, imageLoader, newListPresenter)
    recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(
      this, R.anim.recycler_view_layout_animation
    )
    recyclerView.adapter = recyclerViewAdapter
  }
}
