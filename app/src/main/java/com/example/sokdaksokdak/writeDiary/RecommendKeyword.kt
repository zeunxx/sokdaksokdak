package com.example.sokdaksokdak.writeDiary

class RecommendKeyword() {
    private lateinit var keyList: ArrayList<String>

    init { // == 생성자
        init()
    }

    private fun init(){ // TODO: 키워드 추가
        this.keyList = arrayListOf("하늘", "구름", "풍경", "고찰", "바다", "별", "카메라", "달", "하루", "동물",
            "과제", "감정", "나", "책", "시험", "연애", "공부", "일", "운동", "관심사",
            "핸드폰", "연예인", "노래", "플레이리스트", "핸드폰", "일상", "전화", "추억", "여행", "소풍",
            "약속", "우정", "사랑", "화")
        println("keyword list size: " + this.keyList.size)
        /*
        * 리스트에 365개의 단어 저장 -> 랜덤으로 추출하여 가져오기
        */
    }

    public fun randomKeyword(): String{
        return this.keyList[(Math.random()*(this.keyList.size)).toInt()]
    }
}