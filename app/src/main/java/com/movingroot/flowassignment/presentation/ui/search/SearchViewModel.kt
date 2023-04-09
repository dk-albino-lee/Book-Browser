package com.movingroot.flowassignment.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movingroot.flowassignment.R
import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val searchUseCase: SearchUseCaseNetwork,
    private val searchInDetailUseCase: SearchInDetailUseCase,
    private val addBrowsedRecordUseCase: AddBrowsedRecordUseCase
) : ViewModel() {
    val input = MutableLiveData<String>()
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books
    private val _checkRecentBrowsedRecord = MutableLiveData<Boolean>()
    val checkRecentBrowsedRecord: LiveData<Boolean> get() = _checkRecentBrowsedRecord
    private val _progressing = MutableLiveData<Boolean>()
    val progressing: LiveData<Boolean> get() = _progressing
    private val _noData = MutableLiveData<Boolean>()
    val noData: LiveData<Boolean> get() = _noData
    private val _errorMsg = MutableLiveData<IntArray>()
    val errorMsg: LiveData<IntArray> get() = _errorMsg

    var start = 1
    var total = 0

    init {
        _checkRecentBrowsedRecord.value = false
        _progressing.value = false
        _noData.value = false
        input.value = ""
    }

    fun browse(keyword: String) {
        input.value = keyword
        browse()
    }

    fun toRecentRecords() {
        checkRecentBrowsedRecord.value?.let {
            _checkRecentBrowsedRecord.value = !it
        } ?: run {
            _checkRecentBrowsedRecord.value = true
        }
    }

    fun initializeCheckingRecord() {
        _checkRecentBrowsedRecord.value = false
    }

    fun browse() {
        start = 1
        _books.value = listOf()
        handleBrowsedResult()
    }

    fun requestNextPageBrowsingResult() {
        if (total >= start)
            handleBrowsedResult()
    }

    private fun handleBrowsedResult() {
        _progressing.value = true
        viewModelScope.launch(Dispatchers.IO) {
            getBrowsedResult().run {
                handleApiResultBooks(this)
            }
        }
    }

    private suspend fun handleApiResultBooks(result: ApiResult<List<Book>>?) {
        result?.let { ret ->
            makeBrowsedResult(ret)
        } ?: run {
            withContext(Dispatchers.Main) {
                makeNetworkErrorDialog()
            }
        }
        withContext(Dispatchers.Main) {
            _progressing.value = false
        }
    }

    private suspend fun makeBrowsedResult(result: ApiResult<List<Book>>) {
        makeResponseList(result.items)
        withContext(Dispatchers.Main) {
            processAfterResponse(result)
        }
    }

    private fun makeResponseList(response: List<Book>) {
        val prevList = _books.value?.toMutableList() ?: mutableListOf()
        prevList.addAll(response)
        _books.postValue(prevList)
    }

    private fun processAfterResponse(response: ApiResult<List<Book>>) {
        start += 10
        total = response.total
        _noData.value = response.items.isEmpty()
    }

    private fun makeNetworkErrorDialog() {
        val title = R.string.browse_failed_title
        val msg = R.string.browsed_failed_message
        _errorMsg.value = intArrayOf(title, msg)
    }

    private suspend fun getBrowsedResult(): ApiResult<List<Book>>? {
        input.value!!.run {
            addBrowsedRecordUseCase.execute(this)
            return input.value!!.run {
                getSearchResult(this)
            }
        }
    }

    private suspend fun getSearchResult(keyword: String): ApiResult<List<Book>>? {
        return if (shouldBrowseDetail())
            searchInDetailUseCase.execute(keyword, start)
        else
            searchUseCase.execute(keyword, start)
    }

    private fun shouldBrowseDetail(): Boolean {
        val keyword = input.value!!.trim()
        if (!keyword.contains(" "))
            return false
        return keyword.split(" ").run {
            canBeIsbn(this[0]) && canBeIsbn(this[1])
        }
    }

    private fun canBeIsbn(keyword: String): Boolean = canBeIsbn10(keyword) || canBeIsbn13(keyword)

    private fun canBeIsbn10(keyword: String): Boolean {
        if (keyword.length != 10)
            return false
        for (i in 0 until 9) {
            if (!keyword[i].isDigit())
                return false
        }
        if (!keyword.last().isDigit() && keyword.last() != 'X')
            return false
        return true
    }

    private fun canBeIsbn13(keyword: String): Boolean {
        if (keyword.length != 13)
            return false
        keyword.forEach { char ->
            if (!char.isDigit())
                return false
        }
        return true
    }
}
