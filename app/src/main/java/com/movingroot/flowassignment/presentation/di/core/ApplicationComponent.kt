package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.presentation.di.main.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DaoModule::class,
    DataSourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    SubcomponentModule::class
])
interface ApplicationComponent {

    fun mainComponent(): MainComponent.Factory
}
