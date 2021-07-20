package com.medieverone.core_ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.medieverone.core_utils.extensions.asToastDuration
import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment(), BaseView {

    open fun setupClickListeners() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun showToast(message: String, isLongToast: Boolean) {
        Toast.makeText(requireContext(), message, isLongToast.asToastDuration).show()
    }

    override fun navigateToPreviousScreen() {
        activity?.onBackPressed()
    }
}