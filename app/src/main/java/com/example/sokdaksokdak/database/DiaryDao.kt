package com.example.sokdaksokdak.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface DiaryDao {
    //hyeseon modify
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
    fun getDateData(date:Int):Diary

    @Query("delete from diary_table")
    fun deleteData()

    // TODO: date 함수 사용 -> 시간 제외하고, "년, 월, 일" 만 사용 2022-11-22
    @Query("INSERT INTO diary_table(keyword, date, diary_context) VALUES (:keyword, (select date('now', 'localtime')), :diary_context)")
    fun insertDiaryData(keyword: String, diary_context: String)

    @Query("UPDATE diary_table SET keyword=:keyword, diary_context=:diary_context WHERE date=(select date('now', 'localtime'))")
    fun updateDiaryData(keyword: String, diary_context: String)

    // 날짜로 keyword 가져오기
    @Query("select keyword from diary_table where date=(select date('now', 'localtime'))")
    fun getTodayKeyword(): String

    // 날짜로 keyword 가져오기
    @Query("select exists (select keyword from diary_table where date=(select date('now', 'localtime')))")
    fun isDataExist(): Boolean

    // 날짜로 keyword 가져오기
    //@Query("select keyword from diary_table where date=:date")
    //fun getDataFromDate(date: Long)

    // keyword, context update
    //@Query("update keyword from diary_table where date=:date")
    //fun getDataFromDate(date: Long)
}