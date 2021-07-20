package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.create_user_activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.medieverone.core_ui.BaseFragment
import com.medieverone.feature_timetracker.databinding.FragmentCreateUserActivityBinding
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import kotlinx.coroutines.DelicateCoroutinesApi
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

@DelicateCoroutinesApi
class CreateUserActivityFragment : BaseFragment(), CreateUserActivityView {

    @InjectPresenter
    lateinit var presenter: CreateUserActivityPresenter
    private var _binding: FragmentCreateUserActivityBinding? = null
    private val binding get() = _binding!!


    @ProvidePresenter
    fun providePresenter(): CreateUserActivityPresenter {
        return FeatureTimeTrackerComponentHolder.getComponent()
            .presentersComponent()
            .provideCreateUserActivityPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateUserActivityBinding.inflate(inflater)
        return binding.root
    }

    override fun setupClickListeners() {
        super.setupClickListeners()
        binding.btnCreate.setOnClickListener {
            val tag = binding.etUserActivity.text.toString()
            presenter.onCreateActivityClicked(tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}