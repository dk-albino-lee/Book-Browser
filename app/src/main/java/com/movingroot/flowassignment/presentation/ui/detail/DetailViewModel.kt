package com.movingroot.flowassignment.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    private val _progressing = MutableLiveData<Boolean>()
    val progressing: LiveData<Boolean> get() = _progressing
    private val _toGoBack = MutableLiveData<Boolean>()
    val toGoBack: LiveData<Boolean> get() = _toGoBack

    init {
        _progressing.value = false
        _toGoBack.value = false
    }

    fun startLoading() {
        _progressing.value = true
    }

    fun finishLoading() {
        _progressing.value = false
    }

    fun finishPage() {
        _toGoBack.value = true
    }
}
