package com.movingroot.flowassignment.domain.usecase.recently_browsed

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository

class DeleteBrowsedRecordUseCase(private val repository: BrowsedRecordRepository) {
    suspend fun execute(record: BrowsedRecordEntity): Unit? {
        return try {
            repository.deleteRecord(record)
        } catch (e: Exception) {
            null
        }
    }
}
