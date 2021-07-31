package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.medieverone.core_ui.BaseFragment
import com.medieverone.core_utils.extensions.toFormattedTimeString
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.databinding.FragmentAddTimeBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.adapters.UserActivityAdapter
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.*

@DelicateCoroutinesApi
class AddTimeFragment : BaseFragment(), AddTimeView {

    @InjectPresenter
    lateinit var presenter: AddTimePresenter
    private var _binding: FragmentAddTimeBinding? = null
    private val binding get() = _binding!!
    private var activitiesAdapter: UserActivityAdapter? = null

    @ProvidePresenter
    fun providePresenter(): AddTimePresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideAddTimePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.initActivitiesSpinner()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentArgs = AddTimeFragmentArgs.fromBundle(requireArguments())
        presenter.initTrackedTime(fragmentArgs.trackedTime)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initActivitiesSpinner(activities: List<ActivityEntity>) {
        activitiesAdapter = UserActivityAdapter(requireContext(), object: UserActivityAdapter.Callback {
            override fun onItemClicked(activity: ActivityEntity) {
                presenter.onActivityClicked(activity)
            }
        })
        binding.spinnerUserActivity.adapter = activitiesAdapter
        activitiesAdapter?.items?.addAll(activities)
        activitiesAdapter?.notifyDataSetChanged()
    }

    override fun initTime(timeInMillis: Long) {
        binding.tvTime.text = timeInMillis.toFormattedTimeString
    }

    override fun refreshUserActivityAdapter() {
        activitiesAdapter?.notifyDataSetChanged()
    }

    override fun setupClickListeners() {
        super.setupClickListeners()
        binding.btnAddActivity.setOnClickListener {
            presenter.onAddActivityClicked()
        }
        binding.btnSaveTime.setOnClickListener {
            val timeLogEntity = TimeLogEntity(
                id = (0..Int.MAX_VALUE).random(),
                time = presenter.time,
                date = Date(),
                comment = ""
            )
            presenter.onAddTimeClicked(timeLogEntity)
        }
    }

    override fun onPause() {
        super.onPause()
        activitiesAdapter = null
    }

    override fun navigateToCreateUserActivity() {
        val action = AddTimeFragmentDirections.navigateToCreateUserActivityFragment()
        findNavController().navigate(action)
    }
}