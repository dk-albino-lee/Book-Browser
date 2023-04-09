package com.movingroot.flowassignment.data.repository.book

import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import retrofit2.Response

interface BookDataSource {
    suspend fun getBooks(keyword: String, start: Int): Response<ApiResult<List<Book>>>
    suspend fun getBooksInDetail(keyword: String, start: Int): Response<ApiResult<List<Book>>>
}
