package com.example.sokdaksokdak.writeDiary

import android.app.Application
import com.example.sokdaksokdak.database.AppDatabase

class WriteDiary(application: Application) {
    private val repository = DiaryRepository(application)

    private lateinit var keyword: String
    private lateinit var content: String

    /**
     * WriteDiary
     * 당일(매일) 일기의 첫 초기화 및 일기 DB 업데이트 전반의 함수 작성
     * */

    init {
        this.keyword = "키워드를 선택하세요."
        this.content = "일기를 작성하세요."
    }

    public fun createTodayDiary() {
        insertDiary()
    }

    public fun newDiaryData(keyword:String, content:String){
        this.keyword = keyword
        this.content = content

        updateDiary() // 일기 작성 완료했을 때 update 하는 함수
    }
    public fun insertDiary(){
        repository.insertData("키워드를 선택하세요.", "일기를 작성하세요.")
    }

    public fun getKeyword(): String{
        var keywordDB = repository.getTodayKeyword() // DB 에서 날짜로 keyword 가져오기
        // Diary Dao 이용
        return keywordDB
    }

    fun getDiaryContent(): String {
        return repository.getDiaryContent()
    }

    public fun updateKeyword(keyword:String){
        this.keyword = keyword
        updateDiary()
    }

    // 일기 작성 완료했을 때 update 하는 함수
    public fun updateDiary(){
        repository.updateData(this.keyword, this.content)
        // DataBase 에 keyword 혹은 content update
    }

    fun isDataExists(): Boolean {
        return repository.isDataExists()

    }

    fun deleteData() {
        repository.deleteData()

    }
}