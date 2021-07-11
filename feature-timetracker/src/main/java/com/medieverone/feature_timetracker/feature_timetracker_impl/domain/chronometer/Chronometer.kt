package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.chronometer

interface Chronometer {

    fun onStart()
    fun onStop()

    interface ChronometerCallback {

        fun setOnTickListener(timeInMillis: Long)
        fun onStartTracking()
        fun onStopTracking(timeInMillis: Long)
    }
}