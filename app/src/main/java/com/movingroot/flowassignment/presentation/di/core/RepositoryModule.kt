package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.data.repository.book.BookDataSource
import com.movingroot.flowassignment.data.repository.book.BookRepositoryImpl
import com.movingroot.flowassignment.data.repository.browsed_record.BrowsedRecordDataSource
import com.movingroot.flowassignment.data.repository.browsed_record.BrowsedRecordRepositoryImpl
import com.movingroot.flowassignment.domain.repository.BookRepository
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideBookRepository(dataSource: BookDataSource): BookRepository = BookRepositoryImpl(dataSource)

    @Provides
    fun provideBrowsedRecordRepository(dataSource: BrowsedRecordDataSource): BrowsedRecordRepository =
        BrowsedRecordRepositoryImpl(dataSource)
}
