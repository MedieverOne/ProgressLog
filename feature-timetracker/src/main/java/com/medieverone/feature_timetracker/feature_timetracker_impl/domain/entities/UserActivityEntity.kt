package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities

data class UserActivityEntity(
    val id: Int,
    val tag: String,
    val summaryTimeInMillis: Long = 0L
)