package com.medieverone.core_db.core_db_impl.di

import com.medieverone.core_db.core_db_api.DbClient
import com.medieverone.core_db.core_db_impl.DbClientImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class DbModule {
    @Singleton
    @Binds
    abstract fun provideDbClient(dbClientImpl: DbClientImpl): DbClient
}