package com.medieverone.feature_timetracker.feature_timetracker_api

import com.medieverone.feature_activities.featue_activities_api.ActivitiesApi
import com.medieverone.module_injector.BaseApi

interface FeatureTimeTrackerApi : BaseApi {

    fun getActivitiesApi(): ActivitiesApi
}