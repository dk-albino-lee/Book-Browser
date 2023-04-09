package com.movingroot.flowassignment.domain.usecase.search

import com.movingroot.flowassignment.data.model.BrowsedRecordEntity
import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository

class AddBrowsedRecordUseCase(private val repository: BrowsedRecordRepository) {
    suspend fun execute(keyword: String): Unit? {
        return try {
            repository.addRecord(BrowsedRecordEntity(keyword))
        } catch (e: Exception) {
            null
        }
    }
}
