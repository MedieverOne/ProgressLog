package com.medieverone.feature_activities.feature_activities_impl.domain.usecases

import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity

interface ActivitiesUseCase {

    suspend fun getActivities() : List<ActivityEntity>

    suspend fun getActivityTimeLog(activityId: Int): List<TimeLogEntity>
}