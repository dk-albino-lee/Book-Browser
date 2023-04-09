package com.movingroot.flowassignment.presentation.ui.recently_browsed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecentlyBrowsedViewModel @Inject constructor(
    private val getRecordsUseCase: GetBrowsedRecordsUseCase,
    private val deleteRecordUseCase: DeleteBrowsedRecordUseCase,
    private val clearAllRecordsUseCase: ClearAllBrowsedRecordsUseCase
) : ViewModel() {
    private val _browsedList = MutableLiveData<List<BrowsedRecordEntity>>()
    val browsedList: LiveData<List<BrowsedRecordEntity>> get() = _browsedList
    private val _noData = MutableLiveData<Boolean>()
    val noData: LiveData<Boolean> get() = _noData

    init {
        _noData.value = false
        getRecords()
    }

    private fun getRecords() = viewModelScope.launch(Dispatchers.IO) {
        getRecordsUseCase.execute().run {
            _browsedList.postValue(getRecordsUseCase.execute())
            withContext(Dispatchers.Main) {
                _noData.value = this@run.isNullOrEmpty()
            }
        }
    }

    fun deleteRecord(record: BrowsedRecordEntity) = viewModelScope.launch(Dispatchers.IO) {
        deleteRecordUseCase.execute(record)
        getRecords()
    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        if (_browsedList.value.isNullOrEmpty())
            return@launch
        clearAllRecordsUseCase.execute()
        getRecords()
    }
}
