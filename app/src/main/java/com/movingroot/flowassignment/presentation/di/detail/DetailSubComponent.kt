package com.movingroot.flowassignment.presentation.di.detail

import com.movingroot.flowassignment.presentation.di.core.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface DetailSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailSubComponent
    }
}
