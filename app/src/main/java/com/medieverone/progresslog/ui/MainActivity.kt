package com.medieverone.progresslog.ui

import android.os.Bundle
import com.medieverone.core_ui.base_activity.BaseActivity
import com.medieverone.progresslog.R

class MainActivity : BaseActivity(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}