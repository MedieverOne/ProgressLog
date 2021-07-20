package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log

import com.medieverone.core_ui.BaseView
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AddTimeView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initActivitiesSpinner(activities: List<UserActivityEntity>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initTime(timeInMillis: Long)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToCreateUserActivity()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun refreshUserActivityAdapter()

}