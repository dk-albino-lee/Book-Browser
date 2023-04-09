package com.movingroot.flowassignment.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "browsedRecord")
data class BrowsedRecordEntity(
    @ColumnInfo(name = "keyword")
    val keyword: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
)
