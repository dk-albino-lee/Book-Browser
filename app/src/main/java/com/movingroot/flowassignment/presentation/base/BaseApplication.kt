package com.movingroot.flowassignment.presentation.base

import android.app.Application
import com.movingroot.flowassignment.presentation.di.core.*

class BaseApplication : Application() {
    val applicationComponent: ApplicationComponent =
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .daoModule(DaoModule())
            .dataSourceModule(DataSourceModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .subcomponentModule(SubcomponentModule())
            .build()
}
