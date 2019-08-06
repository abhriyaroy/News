package com.zebrostudio.news.presenter

import com.uber.autodispose.ScopeProvider

interface BaseView {
  fun getScope(): ScopeProvider
}