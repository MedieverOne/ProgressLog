package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.activities_log

import com.medieverone.core_ui.BaseView
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UserActivitiesLogView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initActivitiesLog(items: List<UserActivityEntity>)
}