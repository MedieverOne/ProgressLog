package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.medieverone.core_ui.BaseFragment
import com.medieverone.core_utils.changeVisibility
import com.medieverone.core_utils.extensions.toFormattedTimeString
import com.medieverone.feature_timetracker.databinding.FragmentTimeTrackerBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiver
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiver.Companion.ON_START_TRACKING_ACTION
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiver.Companion.ON_STOP_TRACKING_ACTION
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiver.Companion.ON_TICK_ACTION
import com.medieverone.feature_timetracker.feature_timetracker_impl.receivers.TimeTrackerReceiverCallback
import com.medieverone.feature_timetracker.feature_timetracker_impl.services.TimeTrackerService
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


@DelicateCoroutinesApi
class TimeTrackerFragment : BaseFragment(), TimeTrackerView {

    @InjectPresenter
    lateinit var presenter: TimeTrackerPresenter
    private var _binding: FragmentTimeTrackerBinding? = null
    private val binding get() = _binding!!
    private val timeTrackerReceiver = TimeTrackerReceiver().apply {
        timeTrackerReceiverCallback = object : TimeTrackerReceiverCallback {
            override fun onTickReceived(timeInMillis: Long) {
                presenter.onTickTracking(timeInMillis)
            }

            override fun onStartReceived() {
                presenter.onStartTracking()
            }

            override fun onStopReceived(timeInMillis: Long) {
                presenter.onStopTracking(timeInMillis)
            }
        }
    }

    @ProvidePresenter
    fun providePresenter() : TimeTrackerPresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideTimeTrackerPresenter()
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ON_TICK_ACTION)
        filter.addAction(ON_START_TRACKING_ACTION)
        filter.addAction(ON_STOP_TRACKING_ACTION)
        requireActivity().registerReceiver(timeTrackerReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(timeTrackerReceiver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimeTrackerBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setupClickListeners() {
        super.setupClickListeners()
        with(binding) {
            buttonStartTracking.setOnClickListener {
                presenter.onStartTrackingClicked()
            }
            buttonEndTracking.setOnClickListener {
                presenter.onEndTrackingClicked()
            }
        }
    }

    override fun setStartTrackingButtonVisibility(isVisible: Boolean) {
        binding.buttonStartTracking.changeVisibility(isVisible)
    }

    override fun setEndTrackingButtonVisibility(isVisible: Boolean) {
        binding.buttonEndTracking.changeVisibility(isVisible)
    }

    override fun showTime(timeInMillis: Long) {
        binding.chTimeTracker.text = timeInMillis.toFormattedTimeString
    }

    override fun startService() {
        val intent = Intent(requireActivity(), TimeTrackerService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().startForegroundService(intent)
        } else {
            requireActivity().startService(intent)
        }
    }

    override fun stopService() {
        val intent = Intent(requireActivity(), TimeTrackerService::class.java)

        requireActivity().stopService(intent)
    }

    override fun onStopTracking(timeInMillis: Long) {
        val action = TimeTrackerFragmentDirections.navigateToAddTimeFragment()
        action.setTrackedTime(timeInMillis)
        findNavController().navigate(action)
    }
}