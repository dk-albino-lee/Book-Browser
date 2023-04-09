package com.movingroot.flowassignment.presentation.di.recently_browsed

import androidx.lifecycle.ViewModelProvider
import com.movingroot.flowassignment.domain.usecase.recently_browsed.ClearAllBrowsedRecordsUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.DeleteBrowsedRecordUseCase
import com.movingroot.flowassignment.domain.usecase.recently_browsed.GetBrowsedRecordsUseCase
import com.movingroot.flowassignment.presentation.ui.recently_browsed.RecentlyBrowsedFragment
import com.movingroot.flowassignment.presentation.ui.recently_browsed.RecentlyBrowsedViewModel
import dagger.Module
import dagger.Provides

@Module(subcomponents = [RecentlyBrowsedSubComponent::class])
class RecentlyBrowsedSubModule {

    @Provides
    fun provideRecentlyBrowsedViewModelFactory(
        getRecordsUseCase: GetBrowsedRecordsUseCase,
        deleteRecordUseCase: DeleteBrowsedRecordUseCase,
        clearAllRecordsUseCase: ClearAllBrowsedRecordsUseCase
    ): RecentlyBrowsedViewModelModule =
        RecentlyBrowsedViewModelModule(getRecordsUseCase, deleteRecordUseCase, clearAllRecordsUseCase)

    @Provides
    fun provideRecentlyBrowsedViewModel(
        fragment: RecentlyBrowsedFragment,
        factory: RecentlyBrowsedViewModelModule
    ): RecentlyBrowsedViewModel = ViewModelProvider(fragment, factory)[RecentlyBrowsedViewModel::class.java]
}
