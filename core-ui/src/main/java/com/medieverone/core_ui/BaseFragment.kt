package com.medieverone.core_ui

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment(), BaseView {

    open fun setupClickListeners() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }
}