package com.zebrostudio.news.presenter.details

import com.zebrostudio.news.presenter.details.DetailsContract.DetailsPresenter
import com.zebrostudio.news.presenter.details.DetailsContract.DetailsView
import com.zebrostudio.news.ui.utils.ResourceUtils
import com.zebrostudio.news.R

class DetailsPresenterImpl(private val resourceUtils: ResourceUtils) : DetailsPresenter {

  private var detailsView: DetailsView? = null

  override fun attachView(view: DetailsView) {
    detailsView = view
  }

  override fun detachView() {
    detailsView = null
  }

  override fun decorateView(heading: String?, url: String?) {
    detailsView?.showNewsHeadline(
      heading ?: resourceUtils.getStringRes(R.string.details_activity_default_headline)
    )
    if (url != null) {
      detailsView?.showNewsDetails(url)
    } else {
      detailsView?.showDetailsUrlErrorMessage()
      detailsView?.exitView()
    }
  }

  override fun notifyNewsDetailsLoadingStarted() {
    detailsView?.showWaitLoader()
  }

  override fun notifyNewsDetailsLoadingFinished() {
    detailsView?.hideWaitLoader()
  }
}