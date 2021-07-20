package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import com.medieverone.core_ui.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TimeTrackerView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setStartTrackingButtonVisibility(isVisible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setEndTrackingButtonVisibility(isVisible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTime(timeInMillis: Long)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startService()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun stopService()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onStopTracking(timeInMillis: Long)
}