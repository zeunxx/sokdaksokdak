package com.example.sokdaksokdak.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_table")
    fun getAll(): List<Diary>

    @Insert
    fun insertDiary(diary: Diary)

    @Query("Select * From diary_table")
    fun getDiary() : Diary

    @Delete
    fun delete(diary: Diary)

    @Query("SELECT date FROM diary_table")
    fun getDate(): List<Int>

    @Query("select * from diary_table where date=:date")
    fun getname(date:Int):Diary


    @Query("INSERT INTO diary_table(keyword, date, diary_context) VALUES (:keyword, (select datetime('now', 'localtime')), :diary_context)")
    fun insertDiaryData(keyword: String, diary_context: String)

    // 날짜로 keyword 가져오기
    //@Query("select keyword from diary_table where date=:date")
    //fun getDataFromDate(date: Long)

    // keyword, context update
    //@Query("update keyword from diary_table where date=:date")
    //fun getDataFromDate(date: Long)
}