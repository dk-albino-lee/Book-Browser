package com.movingroot.flowassignment.data.database

import androidx.room.*
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity

@Dao
interface BrowsedRecordDao {

    @Query("Select * FROM browsedRecord")
    suspend fun getAll(): List<BrowsedRecordEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg record: BrowsedRecordEntity)

    @Delete
    suspend fun delete(record: BrowsedRecordEntity)

    @Query("DELETE FROM browsedRecord WHERE keyword = :keyword")
    suspend fun deleteDuplicate(keyword: String)

    @Query("DELETE FROM browsedRecord " +
            "WHERE id = (SELECT id FROM browsedRecord ORDER BY id ASC LIMIT 1)"
    )
    suspend fun deleteFirst()

    @Query("DELETE FROM browsedRecord")
    suspend fun deleteAll()

    @Query("Select * From browsedRecord ORDER BY id DESC LIMIT 1")
    suspend fun getLast(): BrowsedRecordEntity?

    @Update
    suspend fun update(record: BrowsedRecordEntity)
}
