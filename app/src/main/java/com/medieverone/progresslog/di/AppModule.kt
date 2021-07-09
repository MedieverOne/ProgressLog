package com.medieverone.progresslog.di

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.core_db.core_db_impl.di.CoreDbComponent
import com.medieverone.feature_timetracker.feature_timetracker_api.FeatureTimeTrackerApi
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.FeatureTimeTrackerDependencies
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.components.FeatureTimeTrackerComponentHolder
import com.medieverone.progresslog.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFeatureTimeTrackerDependencies(): FeatureTimeTrackerDependencies {
        return object : FeatureTimeTrackerDependencies {
            override fun dbClient(): DbClient {
                return CoreDbComponent.get(App.appContext).dbClient()
            }
        }
    }

    @Provides
    fun provideFeatureTimeTracker(dependencies: FeatureTimeTrackerDependencies) : FeatureTimeTrackerApi {
        FeatureTimeTrackerComponentHolder.init(dependencies)
        return FeatureTimeTrackerComponentHolder.get()
    }


}