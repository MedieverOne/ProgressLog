package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medieverone.core_ui.BaseFragment
import com.medieverone.feature_timetracker.R

class TimeTrackerFragment : BaseFragment(), TimeTrackerView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_tracker, container, false)
    }
}