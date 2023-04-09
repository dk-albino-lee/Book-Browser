package com.movingroot.flowassignment.presentation.di.core

import android.content.Context
import com.movingroot.flowassignment.presentation.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApplication) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideApplication(): BaseApplication = application
}
