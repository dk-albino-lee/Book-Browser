package com.movingroot.flowassignment.presentation.di.recently_browsed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import com.movingroot.flowassignment.presentation.ui.recently_browsed.RecentlyBrowsedViewModel
import javax.inject.Inject

class RecentlyBrowsedViewModelModule @Inject constructor(
    private val getRecordsUseCase: GetBrowsedRecordsUseCase,
    private val deleteRecordUseCase: DeleteBrowsedRecordUseCase,
    private val clearAllRecordsUseCase: ClearAllBrowsedRecordsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecentlyBrowsedViewModel::class.java)) {
            return RecentlyBrowsedViewModel(getRecordsUseCase, deleteRecordUseCase, clearAllRecordsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
