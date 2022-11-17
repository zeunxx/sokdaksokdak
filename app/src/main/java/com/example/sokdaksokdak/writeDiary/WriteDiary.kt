package com.example.sokdaksokdak.writeDiary

class WriteDiary {
    private lateinit var keyword: String
    private lateinit var content: String
    // 날짜는? - (select datetime('now', 'localtime')

    init {
        this.keyword = "키워드를 선택하세요."
        this.content = "일기를 작성하세요."

        insertDiary()
    }

    public fun getKeyword(): String{
        var keywordDB = "키워드를 선택하세요." // DB 에서 날짜로 keyword 가져오기
        // Diary Dao 이용
        return keywordDB
    }

    /*public fun setKeyword(keyword: String){
        this.keyword = keyword
        // DB에 keyword update
    }

    public fun setContent(content: String){
        this.content = content
        // DB에 content update
    }*/

    private fun insertDiary(){
        // DataBase 에 keyword 혹은 content update
    }
}