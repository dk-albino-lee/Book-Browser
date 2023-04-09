package com.movingroot.flowassignment.presentation.di.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import com.movingroot.flowassignment.presentation.ui.search.SearchViewModel
import dagger.Module
import javax.inject.Inject

@Module
class SearchViewModelModule @Inject constructor(
    private val searchUseCase: SearchUseCaseNetwork,
    private val searchInDetailUseCase: SearchInDetailUseCase,
    private val addBrowsedRecordUseCase: AddBrowsedRecordUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchUseCase, searchInDetailUseCase, addBrowsedRecordUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
