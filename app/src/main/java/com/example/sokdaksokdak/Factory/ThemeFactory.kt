package com.example.sokdaksokdak.Factory

import com.example.sokdaksokdak.CalendarFragment
import com.example.sokdaksokdak.DiaryFragment
import com.example.sokdaksokdak.MypageFragment

interface ThemeFactory{

    abstract val activity: Any

    fun createNaviActivity()
    fun createSplashActivity()
}