package com.medieverone.progresslog.di

import com.medieverone.progresslog.di.presenters.PresentersComponent
import com.medieverone.progresslog.ui.MainActivity
import dagger.Component
import dagger.internal.Preconditions
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    ContextModule::class
])
@Singleton
abstract class AppComponent {

    abstract fun presentersComponent(): PresentersComponent
    abstract fun inject(activity: MainActivity)

    companion object {

        @Volatile
        private var instance: AppComponent? = null

        fun get(): AppComponent {
            return Preconditions.checkNotNull(
                instance,
                "AppComponent is not initialized"
            )!!
        }

        fun init(component: AppComponent) {
            require(instance == null) {
                "AppComponent is initialized"
            }
            instance = component
        }
    }
}