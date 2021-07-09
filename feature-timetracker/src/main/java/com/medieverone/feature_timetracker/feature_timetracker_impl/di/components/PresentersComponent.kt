package com.medieverone.feature_timetracker.feature_timetracker_impl.di.components

import com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.TimeTrackerPresenter
import dagger.Subcomponent

@Subcomponent
internal interface PresentersComponent {

    fun provideTimeTrackerPresenter(): TimeTrackerPresenter
}