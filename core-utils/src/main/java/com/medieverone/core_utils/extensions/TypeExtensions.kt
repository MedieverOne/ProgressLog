package com.medieverone.core_utils.extensions

import android.view.View

val Boolean.asVisibility: Int
    get() = if(this) View.VISIBLE else View.GONE