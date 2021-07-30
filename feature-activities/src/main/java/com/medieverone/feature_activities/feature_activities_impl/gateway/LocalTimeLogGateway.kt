package com.medieverone.feature_activities.feature_activities_impl.gateway

import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity

interface LocalTimeLogGateway {

    suspend fun getTimeLog(): List<TimeLogEntity>
    suspend fun saveTimeLogItem(activityId: Int, timeLogEntity: TimeLogEntity)
    suspend fun deleteTimeLogItem(activityId: Int, timeLogId: Int)
}