package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.activities_log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.medieverone.core_ui.BaseFragment
import com.medieverone.feature_timetracker.databinding.FragmentUserActivitiesLogBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.adapters.UserActivitiesLogAdapter
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

@DelicateCoroutinesApi
class UserActivitiesLogFragment : BaseFragment(), UserActivitiesLogView {

    @InjectPresenter
    lateinit var presenter: UserActivitiesLogPresenter
    private var _binding: FragmentUserActivitiesLogBinding? = null
    private val binding get() = _binding!!
    private val userActivitiesLogAdapter =
        UserActivitiesLogAdapter(object : UserActivitiesLogAdapter.Callback {
            override fun onItemRightSlide(item: UserActivityEntity) {
                presenter.deleteUserActivity(item)
            }
        })

    @ProvidePresenter
    fun providePresenter(): UserActivitiesLogPresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideUserActivitiesLogPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserActivitiesLogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvUserActivitiesLog.adapter = userActivitiesLogAdapter
        ItemTouchHelper(userActivitiesLogAdapter.itemTouchCallback)
            .attachToRecyclerView(binding.rvUserActivitiesLog)
    }

    override fun onResume() {
        super.onResume()
        presenter.initActivitiesLog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun initActivitiesLog(items: List<UserActivityEntity>) {
        userActivitiesLogAdapter.initItems(items)
    }
}