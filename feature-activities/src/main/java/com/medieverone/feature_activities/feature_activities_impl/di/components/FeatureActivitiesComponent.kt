package com.medieverone.feature_activities.feature_activities_impl.di.components

import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.feature_activities.feature_activities_impl.di.FeatureActivitiesDependencies
import com.medieverone.feature_activities.feature_activities_impl.di.modules.GatewayModule
import com.medieverone.feature_activities.feature_activities_impl.di.modules.UseCaseModule
import dagger.Component
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton

@DelicateCoroutinesApi
@Component(modules = [
    GatewayModule::class,
    UseCaseModule::class
],
dependencies = [
    FeatureActivitiesDependencies::class
])
@Singleton
internal abstract class FeatureActivitiesComponent : FeatureActivitiesApi {

    abstract fun presentersComponent(): PresentersComponent

    companion object {

        fun initAndGet(dependencies: FeatureActivitiesDependencies): FeatureActivitiesComponent {
            return DaggerFeatureActivitiesComponent.builder()
                .featureActivitiesDependencies(dependencies)
                .build()
        }
    }
}
