package com.medieverone.core_ui

import kotlinx.coroutines.Job
import moxy.MvpPresenter

abstract class BasePresenter<V: BaseView> : MvpPresenter<V>() {

    protected var job: Job? = null

    override fun onDestroy() {
        job?.cancel()
        job = null
        super.onDestroy()
    }
}