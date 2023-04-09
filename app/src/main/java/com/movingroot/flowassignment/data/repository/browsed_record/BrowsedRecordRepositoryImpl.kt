package com.movingroot.flowassignment.data.repository.browsed_record

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrowsedRecordRepositoryImpl @Inject constructor(
    private val dataSource: BrowsedRecordDataSource
) : BrowsedRecordRepository {
    override suspend fun getRecords(): List<BrowsedRecordEntity> {
        return dataSource.getRecords()
    }

    override suspend fun deleteRecord(record: BrowsedRecordEntity) {
        dataSource.deleteRecord(record)
    }

    override suspend fun addRecord(record: BrowsedRecordEntity) {
        dataSource.addRecord(record)
    }

    override suspend fun clearAll() {
        dataSource.clearAll()
    }
}
