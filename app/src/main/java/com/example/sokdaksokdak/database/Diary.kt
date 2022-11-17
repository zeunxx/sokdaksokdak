package com.example.sokdaksokdak.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.Date

@Entity(tableName = "diary_table")
data class Diary(
    @PrimaryKey
    var keyword :String,
    @ColumnInfo(name = "date")
    val date: Int,
    @ColumnInfo(name = "diary_context")
    val diaryContext: String
)