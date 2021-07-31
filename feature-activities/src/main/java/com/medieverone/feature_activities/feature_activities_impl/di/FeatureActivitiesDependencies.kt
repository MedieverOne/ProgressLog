package com.medieverone.feature_activities.feature_activities_impl.di

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.module_injector.BaseDependencies

interface FeatureActivitiesDependencies : BaseDependencies {

    fun dbClient(): DbClient
}