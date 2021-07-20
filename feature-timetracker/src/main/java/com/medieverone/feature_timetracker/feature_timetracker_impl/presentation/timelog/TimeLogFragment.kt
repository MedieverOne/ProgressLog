package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.timelog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medieverone.core_ui.BaseFragment
import com.medieverone.feature_timetracker.databinding.FragmentTimeLogsBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.adapters.TimeLogAdapter
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

@DelicateCoroutinesApi
class TimeLogFragment: BaseFragment(), TimeLogView {

    @InjectPresenter
    lateinit var presenter: TimeLogPresenter
    private var _binding: FragmentTimeLogsBinding? = null
    private val binding get() = _binding!!
    private val adapter: TimeLogAdapter = TimeLogAdapter()

    @ProvidePresenter
    fun providePresenter(): TimeLogPresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideTimeLogPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimeLogsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTimeLog.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initItems(items: List<TimeLogEntity>) {
        adapter.initItems(items)
    }
}