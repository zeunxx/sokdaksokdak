package com.example.sokdaksokdak.writeDiary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.sokdaksokdak.database.AppDatabase

class WriteDiaryViewModel(application: Application): AndroidViewModel(application) {
    var recommendKeyword = RecommendKeyword()
    private var writeDiary = WriteDiary(application)
    // 하루에 한 번 00:00:00 에 선언하도록 -> 기본값 DB에 저장되도록 WriteDiary 에서

    public fun newDiaryData(keyword:String, content:String) {
        writeDiary.newDiaryData(keyword, content)
    }
    public fun insertData() {
        writeDiary.insertDiary()
    }

    // TODO: 키워드 SharedPreference 에 저장
    public fun showKeyword(): String{
        var keywordDB = writeDiary.getKeyword()

        return if (keywordDB == "키워드를 선택하세요."){
            getRandomKeyword()
        } else{
            return keywordDB
        }
        // 만약 DB에 저장된 keyword 가
        // 기본값(키워드를 선택하세요.)이면 getRandomKeyword 호출
        // 기본값이 아니면, getKeyword 호출
    }

    private fun getRandomKeyword(): String {
        return recommendKeyword.randomKeyword()
    }

    /*fun setKeyword(keyword: String) {
        writeDiary.setKeyword(keyword)
    }*/



}