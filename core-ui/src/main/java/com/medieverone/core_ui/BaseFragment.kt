package com.medieverone.core_ui

import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment(), BaseView {

    open fun setupClickListeners() {}
}