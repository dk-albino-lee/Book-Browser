package com.movingroot.flowassignment.presentation.di.search

import com.movingroot.flowassignment.presentation.di.core.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface SearchSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchSubComponent
    }
}
