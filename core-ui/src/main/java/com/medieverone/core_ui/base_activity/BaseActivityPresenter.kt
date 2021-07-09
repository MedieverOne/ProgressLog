package com.medieverone.core_ui.base_activity
import moxy.MvpPresenter

abstract class BaseActivityPresenter<V: BaseActivityView>: MvpPresenter<V>()