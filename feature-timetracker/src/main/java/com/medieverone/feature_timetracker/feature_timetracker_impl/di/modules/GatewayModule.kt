package com.medieverone.feature_timetracker.feature_timetracker_impl.di.modules

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.RealmTimeLogGateway
import com.medieverone.feature_timetracker.feature_timetracker_impl.data.RealmUserActivityGateway
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalTimeLogGateway
import com.medieverone.feature_timetracker.feature_timetracker_impl.gateways.LocalUserActivitiesGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class GatewayModule {

    @Provides
    @Singleton
    fun provideLocalUserActivityGateway(database: DbClient): LocalUserActivitiesGateway {
        return RealmUserActivityGateway(database)
    }

    @Provides
    @Singleton
    fun provideLocalTimeLogGateway(database: DbClient): LocalTimeLogGateway {
        return RealmTimeLogGateway(database)
    }
}