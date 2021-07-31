package com.medieverone.progresslog.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.medieverone.core_navigation.extensions.setupWithNavController
import com.medieverone.core_ui.base_activity.BaseActivity
import com.medieverone.feature_timetracker.feature_timetracker_api.FeatureTimeTrackerApi
import com.medieverone.progresslog.R
import com.medieverone.progresslog.databinding.ActivityMainBinding
import com.medieverone.progresslog.di.AppComponent
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import androidx.navigation.ui.setupActionBarWithNavController
import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var featureTimeTrackerApi: FeatureTimeTrackerApi
    @Inject
    lateinit var featureActivitiesApi: FeatureActivitiesApi

    @InjectPresenter
    lateinit var presenter: MainPresenter


    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding


    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return AppComponent.get().presentersComponent().provideMainPresenter()
    }

    init {
        AppComponent.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        // Important: Ids must be listed in the same order as in the bottom nav bar from left to right
        val navGraphIds = listOf(
            R.navigation.time_tracker_flow,
            R.navigation.activities_flow
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.bottomNavBar.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainNavHostFragment,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}