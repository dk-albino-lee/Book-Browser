package com.movingroot.flowassignment.presentation.utils

import android.os.SystemClock
import android.view.View
import androidx.navigation.NavOptions

object ViewUtil {

    fun getInclusiveNavOption(id: Int): NavOptions {
        return NavOptions.Builder()
            .setPopUpTo(id, inclusive = true)
            .build()
    }

    fun View.onSingleClick(onSingleClick: (View) -> Unit) {
        setOnClickListener(
            SingleClickListener {
                onSingleClick.invoke(it)
            }
        )
    }

    class SingleClickListener(
        private val onSingleClick: (View) -> Unit
    ) : View.OnClickListener {
        private var prevClickedTime = 0L
        private val interval = Constants.CLICK_SAFE_MILLIS

        override fun onClick(v: View) {
            val clickedTime = SystemClock.elapsedRealtime()
            if (clickedTime - prevClickedTime < interval)
                return
            prevClickedTime = clickedTime
            onSingleClick.invoke(v)
        }
    }
}
