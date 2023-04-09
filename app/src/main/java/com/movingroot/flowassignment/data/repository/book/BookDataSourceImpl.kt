package com.movingroot.flowassignment.data.repository.book

import com.movingroot.flowassignment.data.api.ApiService
import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import retrofit2.Response
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : BookDataSource {
    override suspend fun getBooks(keyword: String, start: Int): Response<ApiResult<List<Book>>> {
        return apiService.getBooks(keyword, start = start)
    }

    override suspend fun getBooksInDetail(keyword: String, start: Int): Response<ApiResult<List<Book>>> {
        return apiService.getBookDetails(isbn = keyword, start = start)
    }
}
