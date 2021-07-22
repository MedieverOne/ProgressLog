package com.medieverone.feature_activities.feature_activities_impl.gateway

interface ActivitiesGateway {

    suspend fun getActivities()
    suspend fun addActivity()
    suspend fun deleteActivity()
}