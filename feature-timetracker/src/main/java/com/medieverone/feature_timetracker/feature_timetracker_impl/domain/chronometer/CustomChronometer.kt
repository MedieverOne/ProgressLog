package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.chronometer

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import java.lang.IllegalStateException


@DelicateCoroutinesApi
internal class CustomChronometer(
    private val chronometerCallback: Chronometer.ChronometerCallback? = null,
    private var millisPerTick: Long = COMMON_TICK_DURATION
) : Chronometer {

    @Volatile
    private var trackedTime = 0L
    private var tickerChannel: ReceiveChannel<Unit>? = null
    private var job: Job? = null


    @ObsoleteCoroutinesApi
    override fun onStart() {
        Log.d(TAG, "onStart()")
        tickerChannel = ticker(delayMillis = millisPerTick, initialDelayMillis = millisPerTick)
        job = GlobalScope.launch {
            withContext(Dispatchers.Main) {
                chronometerCallback?.onStartTracking()
            }
            trackedTime = 0L
            if (tickerChannel != null) {
                for (event in tickerChannel!!) {
                    trackedTime += millisPerTick
                    chronometerCallback?.setOnTickListener(trackedTime)
                }
            } else {
                throw IllegalStateException("ReceiveChannel of Custom Chronometer is null")
            }
        }
    }

    override fun onStop() {
        Log.d(TAG, "onStop()")
        chronometerCallback?.onStopTracking(trackedTime)
        tickerChannel?.cancel()
        job?.cancel()

        tickerChannel = null
        job = null
    }

    companion object {

        private const val TAG = "CustomChronometer"
        private const val COMMON_TICK_DURATION = 1000L
    }
}