package com.medieverone.feature_activities.feature_activities_impl.domain.usecases

import com.medieverone.feature_activities.feature_activities_impl.gateway.LocalActivitiesGateway

class ActivitiesUseCaseImpl(
    private val localGateway: LocalActivitiesGateway
): ActivitiesUseCase {

    override suspend fun getActivities() =
        localGateway.getActivities()

    override suspend fun getActivityTimeLog(activityId: Int) =
        localGateway.getActivityTimeLog(activityId)

}