package com.medieverone.feature_activities.feature_activities_impl.presentation

import com.medieverone.core_ui.BaseView
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ActivitiesView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initActivities(activities: List<ActivityEntity>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initTimeLog(timeLog: List<TimeLogEntity>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setErrorVisibility(isVisible: Boolean)

}