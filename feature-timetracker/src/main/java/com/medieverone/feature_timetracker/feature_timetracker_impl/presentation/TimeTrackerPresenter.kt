package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import com.medieverone.core_ui.BasePresenter
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class TimeTrackerPresenter @Inject constructor() : BasePresenter<TimeTrackerView>() {

    private var trackingState: TrackingState = TrackingState.IS_STOP
        set(value) {
            field = value
            changeButtonsVisibilityState(value)
        }

    fun onStartTrackingClicked() {
        trackingState = TrackingState.IS_TRACKING
    }

    fun onEndTrackingClicked() {
        trackingState = TrackingState.IS_STOP
    }

    private fun changeButtonsVisibilityState(state: TrackingState) {
        when (state) {
            TrackingState.IS_TRACKING -> {
                viewState.setStartTrackingButtonVisibility(false)
                viewState.setEndTrackingButtonVisibility(true)
            }
            TrackingState.IS_STOP -> {
                viewState.setStartTrackingButtonVisibility(true)
                viewState.setEndTrackingButtonVisibility(false)
            }
        }
    }

    enum class TrackingState {
        IS_TRACKING, IS_STOP
    }
}