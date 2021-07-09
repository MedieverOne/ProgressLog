package com.medieverone.feature_timetracker.feature_timetracker_impl.di

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.module_injector.BaseDependencies

interface FeatureTimeTrackerDependencies : BaseDependencies {

    fun dbClient(): DbClient
}