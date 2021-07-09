package com.medieverone.feature_timetracker.feature_timetracker_impl.di.components

import com.medieverone.feature_timetracker.feature_timetracker_api.FeatureTimeTrackerApi
import com.medieverone.feature_timetracker.feature_timetracker_impl.di.FeatureTimeTrackerDependencies
import com.medieverone.module_injector.ComponentHolder

object FeatureTimeTrackerComponentHolder :
    ComponentHolder<FeatureTimeTrackerApi, FeatureTimeTrackerDependencies> {

    private var featureTimeTrackerComponent: FeatureTimeTrackerComponent? = null

    override fun init(dependencies: FeatureTimeTrackerDependencies) {
        if (featureTimeTrackerComponent == null) {
            synchronized(FeatureTimeTrackerComponent::class.java) {
                if (featureTimeTrackerComponent == null) {
                    featureTimeTrackerComponent = FeatureTimeTrackerComponent.initAndGet(
                        dependencies
                    )
                }
            }
        }
    }

    override fun get(): FeatureTimeTrackerApi = getComponent()

    override fun reset() {
        featureTimeTrackerComponent = null
    }

    internal fun getComponent(): FeatureTimeTrackerComponent {
        checkNotNull(featureTimeTrackerComponent) {
            "FeatureTimeTrackerComponent isnt initialized"
        }
        return featureTimeTrackerComponent!!
    }
}