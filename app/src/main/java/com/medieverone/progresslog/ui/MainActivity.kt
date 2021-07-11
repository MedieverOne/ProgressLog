package com.medieverone.progresslog.ui

import android.os.Bundle
import com.medieverone.core_ui.base_activity.BaseActivity
import com.medieverone.feature_timetracker.feature_timetracker_api.FeatureTimeTrackerApi
import com.medieverone.progresslog.R
import com.medieverone.progresslog.di.AppComponent
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var featureTimeTrackerApi: FeatureTimeTrackerApi

    @InjectPresenter
    lateinit var presenter: MainPresenter


    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return AppComponent.get().presentersComponent().provideMainPresenter()
    }

    init {
        AppComponent.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}