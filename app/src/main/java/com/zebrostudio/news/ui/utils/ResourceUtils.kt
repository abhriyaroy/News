package com.zebrostudio.news.ui.utils

import android.content.Context
import androidx.annotation.StringRes

interface ResourceUtils {
  fun getStringRes(@StringRes id: Int): String
}

class ResourceUtilsImpl(private val context: Context) : ResourceUtils {
  override fun getStringRes(id: Int) = context.stringRes(id)
}