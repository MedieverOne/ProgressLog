package com.medieverone.feature_timetracker.feature_timetracker_impl.di.components

import com.medieverone.feature_timetracker.feature_timetracker_api.FeatureTimeTrackerApi
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.FeatureTimeTrackerDependencies
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.modules.FeatureTimeTrackerModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        FeatureTimeTrackerModule::class
    ],
    dependencies = [FeatureTimeTrackerDependencies::class]
)
@Singleton
internal abstract class FeatureTimeTrackerComponent : FeatureTimeTrackerApi {

    companion object {

        fun initAndGet(dependencies: FeatureTimeTrackerDependencies) : FeatureTimeTrackerComponent {
            return DaggerFeatureTimeTrackerComponent.builder()
                .featureTimeTrackerDependencies(dependencies)
                .build()
        }
    }
}