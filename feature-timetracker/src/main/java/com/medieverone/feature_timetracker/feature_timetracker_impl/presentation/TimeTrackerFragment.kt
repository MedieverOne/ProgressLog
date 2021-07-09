package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medieverone.core_ui.BaseFragment
import com.medieverone.core_utils.changeVisibility
import com.medieverone.feature_timetracker.databinding.FragmentTimeTrackerBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class TimeTrackerFragment : BaseFragment(), TimeTrackerView {

    @InjectPresenter
    lateinit var presenter: TimeTrackerPresenter
    private var _binding: FragmentTimeTrackerBinding? = null
    private val binding get() = _binding!!


    @ProvidePresenter
    fun providePresenter() : TimeTrackerPresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideTimeTrackerPresenter()
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
}