package com.medieverone.core_ui

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String, isLongToast: Boolean = false)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToPreviousScreen()
}