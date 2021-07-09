package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities

import java.util.*

data class TimeLogEntity(
    val time: Long,
    val date: Date,
    val comment: String
)
