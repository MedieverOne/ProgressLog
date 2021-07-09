package com.medieverone.core_ui

import moxy.MvpPresenter

abstract class BasePresenter<V: BaseView> : MvpPresenter<V>()