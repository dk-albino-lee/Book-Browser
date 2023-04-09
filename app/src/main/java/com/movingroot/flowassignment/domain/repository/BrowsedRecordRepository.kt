package com.movingroot.flowassignment.domain.repository

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity

interface BrowsedRecordRepository {
    suspend fun getRecords(): List<BrowsedRecordEntity>
    suspend fun deleteRecord(record: BrowsedRecordEntity)
    suspend fun addRecord(record: BrowsedRecordEntity)
    suspend fun clearAll()
}
