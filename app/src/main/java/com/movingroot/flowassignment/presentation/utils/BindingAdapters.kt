package com.movingroot.flowassignment.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.movingroot.flowassignment.presentation.utils.ViewUtil.onSingleClick

@BindingAdapter("onSingleClick")
fun View.onSingleClick(callback: Runnable) {
    onSingleClick {
        callback.run()
    }
}
