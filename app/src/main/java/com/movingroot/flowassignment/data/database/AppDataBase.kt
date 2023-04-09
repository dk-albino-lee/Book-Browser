package com.movingroot.flowassignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movingroot.flowassignment.data.model.BrowsedRecordEntity

@Database(entities = [BrowsedRecordEntity::class], exportSchema = false, version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun browsedRecordDao(): BrowsedRecordDao
}
