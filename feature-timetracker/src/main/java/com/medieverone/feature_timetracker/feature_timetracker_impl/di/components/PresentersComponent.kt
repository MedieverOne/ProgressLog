package com.medieverone.feature_timetracker.feature_timetracker_impl.di.components

import com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.TimeTrackerPresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log.AddTimePresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.create_user_activity.CreateUserActivityPresenter
import dagger.Subcomponent
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@Subcomponent
internal interface PresentersComponent {

    fun provideTimeTrackerPresenter(): TimeTrackerPresenter
    fun provideCreateUserActivityPresenter(): CreateUserActivityPresenter
    fun provideAddTimePresenter(): AddTimePresenter
}