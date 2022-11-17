package com.example.sokdaksokdak.writeDiary

import android.app.Application
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary

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

    // TODO: 일기 작성 완료했을 때 update 하는 함수
    fun updateData(keyword: String, content: String){
        val r = Runnable {
            diaryDao.updateDiaryData(keyword, content)
        }

        val thread = Thread(r)
        thread.start()
    }


}