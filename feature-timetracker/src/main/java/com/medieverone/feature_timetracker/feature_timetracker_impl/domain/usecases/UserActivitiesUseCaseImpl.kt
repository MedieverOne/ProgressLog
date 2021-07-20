package com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalUserActivitiesGateway

class UserActivitiesUseCaseImpl(
    private val localUserActivitiesGateway: LocalUserActivitiesGateway
) : UserActivitiesUseCase {

    override suspend fun addUserActivity(userActivityEntity: UserActivityEntity) =
        localUserActivitiesGateway.addUserActivity(userActivityEntity)

    override suspend fun deleteUserActivity(activityTag: String) =
        localUserActivitiesGateway.deleteUserActivity(activityTag)

    override suspend fun getUserActivity(activityTag: String) =
        localUserActivitiesGateway.getUserActivity(activityTag)

    override suspend fun getAllUserActivities() =
        localUserActivitiesGateway.getAllUserActivities()
}