package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.medieverone.core_ui.BaseFragment
import com.medieverone.core_utils.extensions.toFormattedTimeString
import com.medieverone.feature_timetracker.databinding.FragmentAddTimeBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.adapters.UserActivityAdapter
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
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

    override fun initActivitiesSpinner(activities: List<UserActivityEntity>) {
        activitiesAdapter = UserActivityAdapter(requireContext())
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
            val activity = binding.spinnerUserActivity.selectedItem as UserActivityEntity?
            val timeLogEntity = TimeLogEntity(
                time = presenter.time,
                date = Date(),
                comment = "",
                activity = activity
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