package com.medieverone.core_utils.extensions

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import java.util.concurrent.TimeUnit


val Boolean.asVisibility: Int
    get() = if(this) View.VISIBLE else View.GONE

val Long.toFormattedTimeString: String
    @SuppressLint("DefaultLocale")
    get() {
        return String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(this),
            TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(this)
            ),
            TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(this)
            )
        )

    }

val Boolean.asToastDuration: Int
    get() {
        return if(this) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    }