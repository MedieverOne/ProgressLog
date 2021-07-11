package com.medieverone.feature_timetracker.feature_timetracker_impl.receivers

interface TimeTrackerReceiverCallback {

    fun onTickReceived(timeInMillis: Long)
    fun onStartReceived()
    fun onStopReceived(timeInMillis: Long)
}