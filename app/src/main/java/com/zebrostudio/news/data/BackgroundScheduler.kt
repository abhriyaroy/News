package com.zebrostudio.news.data

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

interface BackgroundScheduler {
  fun getIoScheduler(): Scheduler
  fun getComputationScheduler(): Scheduler
}

class BackgroundSchedulerImpl : BackgroundScheduler {
  override fun getIoScheduler() = Schedulers.io()
  override fun getComputationScheduler() = Schedulers.computation()
}