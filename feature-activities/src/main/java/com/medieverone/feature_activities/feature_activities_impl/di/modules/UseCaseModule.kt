package com.medieverone.feature_activities.feature_activities_impl.di.modules

import com.medieverone.feature_activities.feature_activities_impl.domain.usecases.ActivitiesUseCaseImpl
import com.medieverone.feature_activities.feature_activities_impl.gateway.LocalActivitiesGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [GatewayModule::class])
class UseCaseModule {

    @Provides
    @Singleton
    fun provideActivitiesUseCase(
        localActivitiesGateway: LocalActivitiesGateway
    ): ActivitiesUseCaseImpl {
        return ActivitiesUseCaseImpl(localActivitiesGateway)
    }
}