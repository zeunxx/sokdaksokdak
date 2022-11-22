package com.example.sokdaksokdak.writeDiary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.sokdaksokdak.database.AppDatabase

class WriteDiaryViewModel(application: Application): AndroidViewModel(application) {
    var recommendKeyword = RecommendKeyword()
    private var writeDiary = WriteDiary(application)
    // 하루에 한 번 00:00:00 에 선언하도록 -> 기본값 DB에 저장되도록 WriteDiary 에서

    public fun insertData() {
        writeDiary.insertDiary()
    }

    public fun newDiaryData(keyword:String, content:String) {
        writeDiary.newDiaryData(keyword, content)
    }

    public fun checkDataExist(): Boolean{
        return writeDiary.isDataExist()
    }

    // TODO: 키워드 SharedPreference 에 저장
    public fun showKeyword(): String{
        var isExists = checkDataExist()

        if (!isExists) {
            println("Insert New Data")
            insertData()
        }
        println("after insertData")

        var keywordDB = writeDiary.getKeyword()

        println("현재 저장 keyword: " + keywordDB)

        return if (keywordDB == "키워드를 선택하세요."){
            println("Get Random Keyword")
            getRandomKeyword()
        } else{
            return keywordDB
        }
        // 만약 DB에 저장된 keyword 가
        // 기본값(키워드를 선택하세요.)이면 getRandomKeyword 호출
        // 기본값이 아니면, getKeyword 호출
    }

    private fun getRandomKeyword(): String {
        val keyword = recommendKeyword.randomKeyword()
        writeDiary.updateKeyword(keyword)
        return keyword
    }

    fun deleteData() {
        writeDiary.deleteData()
    }

    /*fun setKeyword(keyword: String) {
        writeDiary.setKeyword(keyword)
    }*/



}