package com.movingroot.flowassignment.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movingroot.flowassignment.presentation.di.core.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor() : ViewModel() {
    private val _selectedKeyword = MutableLiveData<String>()
    val selectedKeyword: LiveData<String> get() = _selectedKeyword

    fun selectBrowsedRecord(keyword: String) {
        _selectedKeyword.value = keyword
    }
}
