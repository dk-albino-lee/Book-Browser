package com.movingroot.flowassignment.domain.usecase.recently_browsed

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository

class GetBrowsedRecordsUseCase(private val repository: BrowsedRecordRepository) {
    suspend fun execute(): List<BrowsedRecordEntity>? {
        return try {
            repository.getRecords()
        } catch (e: Exception) {
            null
        }
    }
}
