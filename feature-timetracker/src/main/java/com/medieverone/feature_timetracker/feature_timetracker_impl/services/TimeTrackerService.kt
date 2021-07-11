package com.medieverone.feature_timetracker.feature_timetracker_impl.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import com.medieverone.feature_timetracker.R
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.chronometer.Chronometer
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.chronometer.CustomChronometer
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiver
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class TimeTrackerService : Service(), Chronometer.ChronometerCallback {

    private var chronometer: Chronometer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        chronometer = CustomChronometer(chronometerCallback = this)
        runAsForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        chronometer?.onStart()
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        chronometer?.onStop()
        chronometer = null
        super.onDestroy()
    }

    override fun setOnTickListener(timeInMillis: Long) {
        val intent = Intent(TimeTrackerReceiver.ON_TICK_ACTION)
        intent.putExtra(PARAM_TIME_IN_MILLIS, timeInMillis)
        sendBroadcast(intent)
    }

    override fun onStartTracking() {
        val intent = Intent(TimeTrackerReceiver.ON_START_TRACKING_ACTION)
        sendBroadcast(intent)
    }

    override fun onStopTracking(timeInMillis: Long) {
        val intent = Intent(TimeTrackerReceiver.ON_STOP_TRACKING_ACTION)
        intent.putExtra(PARAM_TIME_IN_MILLIS, timeInMillis)
        sendBroadcast(intent)
    }

    private fun runAsForegroundService() {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            } else {
                ""
            }
        val notificationBuilder = NotificationCompat.Builder(this, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setPriority(PRIORITY_MAX)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentText("Test")
            .build()
        startForeground(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String{
        val channelId = "TimeTrackerChannelId"
        val channelName = "TimeTrackerService"
        val channel = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.lightColor = Color.BLUE
        channel.importance = NotificationManager.IMPORTANCE_DEFAULT
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    companion object {
        const val PARAM_TIME_IN_MILLIS = "timeInMillis"
    }
}