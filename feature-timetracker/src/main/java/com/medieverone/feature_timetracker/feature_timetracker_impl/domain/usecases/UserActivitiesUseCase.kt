package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity

interface UserActivitiesUseCase {

    suspend fun addUserActivity(userActivityEntity: UserActivityEntity)
    suspend fun deleteUserActivity(activityTag: String)
    suspend fun getUserActivity(activityTag: String): UserActivityEntity
    suspend fun getAllUserActivities(): List<UserActivityEntity>
}