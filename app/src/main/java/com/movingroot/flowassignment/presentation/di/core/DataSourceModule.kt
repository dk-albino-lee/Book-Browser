package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.data.api.ApiService
import com.movingroot.flowassignment.data.database.BrowsedRecordDao
import com.movingroot.flowassignment.data.repository.book.BookDataSource
import com.movingroot.flowassignment.data.repository.book.BookDataSourceImpl
import com.movingroot.flowassignment.data.repository.browsed_record.BrowsedRecordDataSource
import com.movingroot.flowassignment.data.repository.browsed_record.BrowsedRecordDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideBookDataSource(apiService: ApiService): BookDataSource = BookDataSourceImpl(apiService)

    @Provides
    fun provideBrowsedRecordDataSource(dao: BrowsedRecordDao): BrowsedRecordDataSource = BrowsedRecordDataSourceImpl(dao)
}
