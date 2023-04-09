package com.movingroot.flowassignment.data.repository.browsed_record

import com.movingroot.flowassignment.data.database.BrowsedRecordDao
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import javax.inject.Inject

class BrowsedRecordDataSourceImpl @Inject constructor(
    private val dao: BrowsedRecordDao
) : BrowsedRecordDataSource {
    override suspend fun getRecords(): List<BrowsedRecordEntity> {
        return dao.getAll() ?: listOf()
    }

    override suspend fun deleteRecord(record: BrowsedRecordEntity) {
        dao.delete(record)
    }

    override suspend fun addRecord(record: BrowsedRecordEntity) {
        add(record)
        val rowCnt = dao.getAll()?.size ?: 0
        if (rowCnt == 10) {
            dao.deleteFirst()
        }
    }

    private suspend fun add(record: BrowsedRecordEntity) {
        dao.deleteDuplicate(record.keyword)
        dao.add(record)
    }

    override suspend fun clearAll() {
        dao.deleteAll()
    }
}
