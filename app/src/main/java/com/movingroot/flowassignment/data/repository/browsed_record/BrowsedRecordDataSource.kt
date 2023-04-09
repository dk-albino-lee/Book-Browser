package com.movingroot.flowassignment.data.repository.browsed_record

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity

interface BrowsedRecordDataSource {
    suspend fun getRecords(): List<BrowsedRecordEntity>
    suspend fun deleteRecord(record: BrowsedRecordEntity)
    suspend fun addRecord(record: BrowsedRecordEntity)
    suspend fun clearAll()
}
