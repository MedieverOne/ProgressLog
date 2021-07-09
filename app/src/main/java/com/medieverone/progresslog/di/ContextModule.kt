package com.medieverone.progresslog.di

import android.content.Context
import com.medieverone.progresslog.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun provideContext(): Context = App.appContext
}