package com.movingroot.flowassignment.domain.usecase.recently_browsed

import com.movingroot.flowassignment.domain.repository.BrowsedRecordRepository

class ClearAllBrowsedRecordsUseCase(private val repository: BrowsedRecordRepository) {
    suspend fun execute(): Unit? {
        return try {
            repository.clearAll()
        } catch (e: Exception) {
            null
        }
    }
}
