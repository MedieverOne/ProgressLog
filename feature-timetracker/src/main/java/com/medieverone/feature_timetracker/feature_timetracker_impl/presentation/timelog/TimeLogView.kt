package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.timelog

import com.medieverone.core_ui.BaseView
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface TimeLogView: BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun initItems(items: List<TimeLogEntity>)
}