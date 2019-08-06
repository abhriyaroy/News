package com.zebrostudio.news.ui.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.stringRes(@StringRes id: Int) = getString(id)!!

fun Context.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
  Toast.makeText(this, message, length).show()
}