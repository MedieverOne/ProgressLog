package com.medieverone.feature_activities.feature_activities_impl.di

import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.module_injector.ComponentHolder

object FeatureActivitiesComponentHolder :
    ComponentHolder<FeatureActivitiesApi, FeatureActivitiesDependencies> {

    private var featureActivitiesComponent: FeatureActivitiesComponent? = null

    override fun init(dependencies: FeatureActivitiesDependencies) {
        if (featureActivitiesComponent == null) {
            synchronized(FeatureActivitiesComponent::class.java ) {
                
            }
        }
    }

    override fun get(): FeatureActivitiesApi {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}