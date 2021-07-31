package com.medieverone.feature_activities.feature_activities_impl.di.components

import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.feature_activities.feature_activities_impl.di.FeatureActivitiesComponent
import com.medieverone.feature_activities.feature_activities_impl.di.FeatureActivitiesDependencies
import com.medieverone.module_injector.ComponentHolder

object FeatureActivitiesComponentHolder :
    ComponentHolder<FeatureActivitiesApi, FeatureActivitiesDependencies> {

    private var featureActivitiesComponent: FeatureActivitiesComponent? = null

    override fun init(dependencies: FeatureActivitiesDependencies) {
        if (featureActivitiesComponent == null) {
            synchronized(FeatureActivitiesComponent::class.java ) {
                if(featureActivitiesComponent == null) {
                    featureActivitiesComponent = FeatureActivitiesComponent.initAndGet(
                        dependencies
                    )
                }
            }
        }
    }

    override fun get(): FeatureActivitiesApi = getComponent()

    override fun reset() {
        featureActivitiesComponent = null
    }

    private fun getComponent(): FeatureActivitiesComponent {
        checkNotNull(featureActivitiesComponent) {
            "FeatureActivitiesComponent isn't initialized"
        }
        return featureActivitiesComponent!!
    }
}