package com.movingroot.flowassignment.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import com.movingroot.flowassignment.presentation.ui.detail.DetailViewModel
import com.movingroot.flowassignment.presentation.ui.recently_browsed.RecentlyBrowsedViewModel
import com.movingroot.flowassignment.presentation.ui.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val searchUseCase: SearchUseCaseNetwork? = null,
    private val searchInDetailUseCase: SearchInDetailUseCase? = null,
    private val addBrowsedRecordUseCase: AddBrowsedRecordUseCase? = null,
    private val getRecordsUseCase: GetBrowsedRecordsUseCase? = null,
    private val deleteRecordUseCase: DeleteBrowsedRecordUseCase? = null,
    private val clearAllRecordsUseCase: ClearAllBrowsedRecordsUseCase? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(searchUseCase!!, searchInDetailUseCase!!, addBrowsedRecordUseCase!!) as T
            }
            modelClass.isAssignableFrom(RecentlyBrowsedViewModel::class.java) -> {
                RecentlyBrowsedViewModel(getRecordsUseCase!!, deleteRecordUseCase!!, clearAllRecordsUseCase!!) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknwon ViewModel calss")
        }
    }
}
