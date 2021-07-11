package com.medieverone.core_utils

import android.view.View
import com.medieverone.core_utils.extensions.asVisibility

fun View.changeVisibility(isVisible: Boolean) {
    visibility = isVisible.asVisibility
}