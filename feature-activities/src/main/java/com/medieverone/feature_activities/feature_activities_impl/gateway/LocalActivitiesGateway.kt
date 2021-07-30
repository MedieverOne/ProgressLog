package com.medieverone.feature_activities.feature_activities_impl.gateway

import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity

interface LocalActivitiesGateway {

    suspend fun getActivities(): List<ActivityEntity>
    suspend fun addActivity(activityEntity: ActivityEntity)
    suspend fun deleteActivity(activityId: Int)
    suspend fun getActivityTimeLog(activityId: Int): List<TimeLogEntity>
}