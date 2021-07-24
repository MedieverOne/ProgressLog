package com.medieverone.feature_activities.feature_activities_impl.di

import dagger.Component

@Component(modules = [
    GatewayModule::class,
    UseCaseModule::class
],
dependencies = [
    FeatureActivitiesDependencies::class
])
internal abstract class FeatureActivitiesComponent {

    companion object {

        fun initAndGet(dependencies: FeatureActivitiesDependencies): FeatureActivitiesComponent {
            return DaggerFeatureActivitiesComponent.builder()
                .featureActivitiesDependencies(dependencies)
                .build()
        }
    }
}
