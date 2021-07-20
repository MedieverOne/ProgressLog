package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import com.medieverone.core_ui.BasePresenter
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class TimeTrackerPresenter @Inject constructor(
) : BasePresenter<TimeTrackerView>() {

    fun onStartTrackingClicked() {
        viewState.startService()
    }

    fun onEndTrackingClicked() {
        viewState.stopService()
    }

    private fun changeButtonsVisibilityState(isTracking: Boolean) {
        viewState.setStartTrackingButtonVisibility(!isTracking)
        viewState.setEndTrackingButtonVisibility(isTracking)
    }

    fun onTickTracking(timeInMillis: Long) {
        viewState.showTime(timeInMillis = timeInMillis)
        changeButtonsVisibilityState(true)
    }

    fun onStartTracking() {
        viewState.showTime(0)
        changeButtonsVisibilityState(true)
    }

    fun onStopTracking(timeInMillis: Long) {
        viewState.onStopTracking(timeInMillis)
        changeButtonsVisibilityState(false)
    }

}