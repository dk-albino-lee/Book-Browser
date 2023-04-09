package com.movingroot.flowassignment.presentation.di.core

import androidx.room.Room
import com.movingroot.flowassignment.data.database.AppDataBase
import com.movingroot.flowassignment.data.database.BrowsedRecordDao
import com.movingroot.flowassignment.presentation.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Singleton
    @Provides
    fun provideBrowsedRecordDao(dataBase: AppDataBase): BrowsedRecordDao = dataBase.browsedRecordDao()

    @Singleton
    @Provides
    fun provideAppDatabase(application: BaseApplication): AppDataBase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDataBase::class.java,
            "app_database"
        ).build()
    }
}
