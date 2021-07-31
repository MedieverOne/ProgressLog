package com.medieverone.feature_activities.feature_activities_impl.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medieverone.core_ui.BaseFragment
import com.medieverone.feature_activities.databinding.FragmentActivitiesBinding
import com.medieverone.feature_activities.feature_activities_impl.adapters.ActivitiesAdapter
import com.medieverone.feature_activities.feature_activities_impl.adapters.TimeLogAdapter
import com.medieverone.feature_activities.feature_activities_impl.di.components.FeatureActivitiesComponent
import com.medieverone.feature_activities.feature_activities_impl.di.components.FeatureActivitiesComponentHolder
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

@DelicateCoroutinesApi
class ActivitiesFragment : BaseFragment(), ActivitiesView {

    @InjectPresenter
    lateinit var presenter: ActivitiesPresenter
    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!
    private var timeLogAdapter: TimeLogAdapter? = null
    private var activitiesAdapter: ActivitiesAdapter? = null

    @ProvidePresenter
    fun providePresenter(): ActivitiesPresenter {
        return FeatureActivitiesComponentHolder
            .get()
            .presentersComponent()
            .provideActivitiesPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeLogAdapter = TimeLogAdapter()
        activitiesAdapter = ActivitiesAdapter()

        with(binding) {
            rvActivities.adapter = activitiesAdapter
            rvTimeLog.adapter = timeLogAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timeLogAdapter = null
        activitiesAdapter = null
    }

    override fun initActivities(activities: List<ActivityEntity>) {
        activitiesAdapter?.initItems(activities)
    }

    override fun initTimeLog(timeLog: List<TimeLogEntity>) {
        timeLogAdapter?.initItems(timeLog)
    }

    override fun setErrorVisibility(isVisible: Boolean) {
        showToast("Empty")
    }
}