package com.movingroot.flowassignment.presentation.di.detail

import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.presentation.ui.detail.DetailFragment
import com.movingroot.flowassignment.presentation.ui.detail.DetailViewModel
import dagger.Module
import dagger.Provides

@Module(subcomponents = [DetailSubComponent::class])
class DetailSubModule {

    @Provides
    fun provideDetailViewModelFactory(): DetailViewModelModule = DetailViewModelModule()

    @Provides
    fun provideDetailViewModel(
        fragment: DetailFragment,
        factory: DetailViewModelModule
    ): DetailViewModel = ViewModelProvider(fragment, factory)[DetailViewModel::class.java]
}
