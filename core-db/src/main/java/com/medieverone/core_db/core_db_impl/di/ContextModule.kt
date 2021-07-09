package com.medieverone.core_db.core_db_impl.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext() = context
}