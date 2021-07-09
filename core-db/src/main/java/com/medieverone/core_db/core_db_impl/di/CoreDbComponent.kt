package com.medieverone.core_db.core_db_impl.di

import android.content.Context
import com.medieverone.core_db.core_db_api.CoreDbApi
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DbModule::class,
        ContextModule::class
    ]
)
@Singleton
abstract class CoreDbComponent : CoreDbApi {

    companion object {

        @Volatile
        private var coreDbComponent: CoreDbComponent? = null

        fun get(context: Context): CoreDbComponent {
            if (coreDbComponent == null) {
                synchronized(CoreDbComponent::class.java) {
                    if (coreDbComponent == null) {
                        coreDbComponent = DaggerCoreDbComponent
                            .builder()
                            .contextModule(ContextModule(context))
                            .build()
                    }
                }
            }
            return coreDbComponent!!
        }
    }
}