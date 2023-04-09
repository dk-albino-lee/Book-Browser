package com.movingroot.flowassignment.presentation.di.main

import com.movingroot.flowassignment.presentation.di.core.ActivityScope
import com.movingroot.flowassignment.presentation.ui.MainActivity
import com.movingroot.flowassignment.presentation.ui.detail.DetailFragment
import com.movingroot.flowassignment.presentation.ui.recently_browsed.RecentlyBrowsedFragment
import com.movingroot.flowassignment.presentation.ui.search.SearchFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(detailFragment: DetailFragment)
    fun inject(recentlyBrowsedFragment: RecentlyBrowsedFragment)
}
