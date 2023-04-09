package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.domain.repository.BookRepository
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.search.AddBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchInDetailUseCase
import com.movingroot.flowassignment.domain.usecase.search.SearchUseCaseNetwork
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideSearchUseCase(repository: BookRepository) = SearchUseCaseNetwork(repository)

    @Provides
    fun provideSearchInDetailUseCase(repository: BookRepository) = SearchInDetailUseCase(repository)

    @Provides
    fun provideAddBrowsedRecordUseCase(repository: BrowsedRecordRepository) = AddBrowsedRecordUseCase(repository)

    @Provides
    fun provideGetBrowsedRecordsUseCase(repository: BrowsedRecordRepository) = GetBrowsedRecordsUseCase(repository)

    @Provides
    fun provideDeleteBrowsedRecordUseCase(repository: BrowsedRecordRepository) = DeleteBrowsedRecordUseCase(repository)

    @Provides
    fun provideClearAllBrowsedRecordsUseCase(repository: BrowsedRecordRepository) = ClearAllBrowsedRecordsUseCase(repository)
}
