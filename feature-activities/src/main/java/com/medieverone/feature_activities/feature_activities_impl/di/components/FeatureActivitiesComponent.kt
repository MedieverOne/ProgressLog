package com.medieverone.feature_activities.feature_activities_impl.di

import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.feature_activities.feature_activities_impl.di.modules.GatewayModule
import com.medieverone.feature_activities.feature_activities_impl.di.modules.UseCaseModule
import dagger.Component

@Component(modules = [
    GatewayModule::class,
    UseCaseModule::class
],
dependencies = [
    FeatureActivitiesDependencies::class
])
internal abstract class FeatureActivitiesComponent : FeatureActivitiesApi {

    companion object {

        fun initAndGet(dependencies: FeatureActivitiesDependencies): FeatureActivitiesComponent {
            return DaggerFeatureActivitiesComponent.builder()
                .featureActivitiesDependencies(dependencies)
                .build()
        }
    }
}
