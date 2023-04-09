package com.movingroot.flowassignment.presentation.di.recently_browsed

import com.movingroot.flowassignment.presentation.di.core.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface RecentlyBrowsedSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RecentlyBrowsedSubComponent
    }
}
