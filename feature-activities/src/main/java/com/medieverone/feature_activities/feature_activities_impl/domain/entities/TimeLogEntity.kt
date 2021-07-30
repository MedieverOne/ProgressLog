package com.medieverone.feature_activities.feature_activities_impl.domain.entities

data class TimeLogEntity(
    val id: Int,
    val comment: String,
    val timeInMillis: Long = 0
)