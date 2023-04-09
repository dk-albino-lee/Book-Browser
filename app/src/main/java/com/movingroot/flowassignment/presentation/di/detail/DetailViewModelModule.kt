package com.movingroot.flowassignment.presentation.di.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.presentation.ui.detail.DetailViewModel
import javax.inject.Inject

class DetailViewModelModule @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
