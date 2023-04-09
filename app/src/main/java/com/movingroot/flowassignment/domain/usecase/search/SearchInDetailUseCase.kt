package com.movingroot.flowassignment.domain.usecase.search

import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import com.movingroot.flowassignment.domain.repository.BookRepository
import com.movingroot.flowassignment.domain.usecase.UseCaseNetworkBase

class SearchInDetailUseCase(private val repository: BookRepository) : UseCaseNetworkBase() {
    suspend fun execute(keyword: String, start: Int): ApiResult<List<Book>>? {
        return getNullableResult {
            repository.searchDetails(keyword, start)
        }
    }
}
