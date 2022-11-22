package com.example.sokdaksokdak.writeDiary

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary
import com.example.sokdaksokdak.database.DiaryDao

class DiaryRepository(application: Application) {
    private val database = AppDatabase.getInstance(application)!!
    private val diaryDao = database.diaryDao()

    fun getAll(): List<Diary>{
        return diaryDao.getAll()
    }

    fun insertData(keyword:String, content:String) {
        val r = Runnable {
            diaryDao.insertDiaryData(keyword, content)
        }

        val thread = Thread(r)
        thread.start()
    }

    fun updateData(keyword: String, content: String){
        val r = Runnable {
            diaryDao.updateDiaryData(keyword, content)
        }

        val thread = Thread(r)
        thread.start()
    }

    // TODO: 반환이 제대로 안 되는 문제
    fun getTodayKeyword(): String {
        var todayKeyword: String = "Get Today Keyword"

        val r = Runnable {
            todayKeyword = diaryDao.getTodayKeyword()
            println("\ntodayKeyword from DB in Runnable: "+todayKeyword)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getTodayKeyword")
        }

        return todayKeyword
    }

    // TODO: 반환이 제대로 안 되는 문제
    fun isDataExist(): Boolean {
        var isExist: Boolean = false
        println("isExist: " + isExist)

        val r = Runnable {
            isExist = diaryDao.isDataExist()
            println("isExist: " + isExist)
        }

        val thread = Thread(r)
        thread.start()
        try {
            thread.join()
        } catch (e: Exception) {
            println("thread join exception in getTodayKeyword")
        }
        println("isExist: " + isExist)
        return isExist
    }

    fun deleteData() {
        val r = Runnable {
            diaryDao.deleteData()
        }
        val thread = Thread(r)
        thread.start()
    }


}