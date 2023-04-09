package com.movingroot.flowassignment.presentation.ui.recently_browsed

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity

interface RecentlyBrowsedDelegate {
    fun browse(keyword: String)
    fun delete(record: BrowsedRecordEntity)
}
