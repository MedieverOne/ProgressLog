package com.medieverone.feature_activities.feature_activities_impl.domain

data class ActivityEntity(
    val id: Int,
    val name: String,
    val logsIds: List<String>?,
    val timeInMillis: Long = 0
) {
    init {
        if (name.isBlank()) {
            throw Throwable("User Activity name can't be null")
        }
    }
}