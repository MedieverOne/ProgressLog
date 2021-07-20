package com.medieverone.feature_timetracker.feature_timetracker_impl.gateways

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity

interface LocalUserActivitiesGateway {

    suspend fun addUserActivity(userActivityEntity: UserActivityEntity)

    suspend fun getUserActivity(activityTag: String): UserActivityEntity

    suspend fun getAllUserActivities(): List<UserActivityEntity>

    suspend fun deleteUserActivity(activityTag: String)
}