package com.medieverone.feature_timetracker.feature_timetracker_impl.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.medieverone.feature_timetracker.feature_timetracker_impl.services.TimeTrackerService.Companion.PARAM_TIME_IN_MILLIS
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class TimeTrackerReceiver : BroadcastReceiver() {

    var timeTrackerReceiverCallback: TimeTrackerReceiverCallback? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            ON_TICK_ACTION -> {
                val time = intent.extras?.getLong(PARAM_TIME_IN_MILLIS)
                if (time != null) {
                    timeTrackerReceiverCallback?.onTickReceived(time)
                }
            }
            ON_START_TRACKING_ACTION -> {
                timeTrackerReceiverCallback?.onStartReceived()
            }
            ON_STOP_TRACKING_ACTION -> {
                val time = intent.extras?.getLong(PARAM_TIME_IN_MILLIS)
                if (time != null) {
                    timeTrackerReceiverCallback?.onStopReceived(time)
                }
            }
        }
    }

    companion object {

        const val ON_TICK_ACTION = "com.medieverone.action.TIME_TRACKER_ON_TICK"
        const val ON_START_TRACKING_ACTION = "com.medieverone.action.TIME_TRACKER_ON_START"
        const val ON_STOP_TRACKING_ACTION = "com.medieverone.action.TIME_TRACKER_ON_STOP"
    }
}