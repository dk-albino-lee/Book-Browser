package com.movingroot.flowassignment.presentation.di.search

import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import com.movingroot.flowassignment.presentation.ui.search.SearchFragment
import com.movingroot.flowassignment.presentation.ui.search.SearchViewModel
import dagger.Module
import dagger.Provides

@Module(subcomponents = [SearchSubComponent::class])
class SearchSubComponentModule {

    @Provides
    fun provideSearchViewModelFactory(
        searchUseCase: SearchUseCaseNetwork,
        searchInDetailUseCase: SearchInDetailUseCase,
        addBrowsedRecordUseCase: AddBrowsedRecordUseCase
    ): SearchViewModelModule =
        SearchViewModelModule(searchUseCase, searchInDetailUseCase, addBrowsedRecordUseCase)

    @Provides
    fun provideSearchViewModel(
        fragment: SearchFragment,
        factory: SearchViewModelModule
    ): SearchViewModel = ViewModelProvider(fragment, factory)[SearchViewModel::class.java]
}
