package com.medieverone.feature_activities.feature_activities_impl.di.components

import com.medieverone.feature_activities.feature_activities_impl.presentation.ActivitiesPresenter
import dagger.Subcomponent
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@Subcomponent
internal interface PresentersComponent {

    fun provideActivitiesPresenter(): ActivitiesPresenter
}