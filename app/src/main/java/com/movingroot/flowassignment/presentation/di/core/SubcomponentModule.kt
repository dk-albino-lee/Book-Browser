package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.presentation.di.detail.DetailSubComponent
import com.movingroot.flowassignment.presentation.di.main.MainComponent
import com.movingroot.flowassignment.presentation.di.recently_browsed.RecentlyBrowsedSubComponent
import com.movingroot.flowassignment.presentation.di.search.SearchSubComponent
import dagger.Module

@Module(subcomponents = [
    MainComponent::class,
    SearchSubComponent::class,
    DetailSubComponent::class,
    RecentlyBrowsedSubComponent::class
])
class SubcomponentModule {
}
