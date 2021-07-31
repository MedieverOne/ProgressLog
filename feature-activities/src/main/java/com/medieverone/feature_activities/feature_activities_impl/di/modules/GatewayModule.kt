package com.medieverone.feature_activities.feature_activities_impl.di.modules

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.feature_activities.feature_activities_impl.data.RealmActivitiesGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GatewayModule {

    @Singleton
    @Provides
    fun provideLocalActivitiesGateway(
        dbClient: DbClient
    ): RealmActivitiesGateway {
        return RealmActivitiesGateway(dbClient)
    }
}