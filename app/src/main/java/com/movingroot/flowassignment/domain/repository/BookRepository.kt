package com.movingroot.flowassignment.domain.repository

import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book

interface BookRepository {
    suspend fun search(keyword: String, start: Int): ApiResult<List<Book>>
    suspend fun searchDetails(keyword: String, start: Int): ApiResult<List<Book>>
}
