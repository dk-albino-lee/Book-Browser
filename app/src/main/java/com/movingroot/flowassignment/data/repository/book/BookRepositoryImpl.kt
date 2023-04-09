package com.movingroot.flowassignment.data.repository.book

import com.movingroot.flowassignment.data.model.ApiResult
import com.movingroot.flowassignment.data.model.Book
import com.movingroot.flowassignment.domain.repository.BookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookDataSource: BookDataSource
) : BookRepository {
    override suspend fun search(keyword: String, start: Int): ApiResult<List<Book>> {
        return bookDataSource.getBooks(keyword, start).body()!!
    }

    override suspend fun searchDetails(keyword: String, start: Int): ApiResult<List<Book>> {
        return bookDataSource.getBooksInDetail(keyword, start).body()!!
    }
}
