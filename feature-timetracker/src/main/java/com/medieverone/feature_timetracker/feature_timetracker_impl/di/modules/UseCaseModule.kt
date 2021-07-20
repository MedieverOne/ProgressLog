package com.medieverone.feature_timetracker.feature_timetracker_impl.di.modules

import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.TimeLogUseCase
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.TimeLogUseCaseImpl
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.UserActivitiesUseCase
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.UserActivitiesUseCaseImpl
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalTimeLogGateway
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalUserActivitiesGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class UseCaseModule {

    @Provides
    @Singleton
    fun provideUserActivitiesUseCase(
        localUserActivitiesGateway: LocalUserActivitiesGateway
    ): UserActivitiesUseCase {
        return UserActivitiesUseCaseImpl(localUserActivitiesGateway)
    }

    @Provides
    @Singleton
    fun provideTimeLogUseCase(
        localTimeLogGateway: LocalTimeLogGateway
    ): TimeLogUseCase {
        return TimeLogUseCaseImpl(localTimeLogGateway)
    }
}