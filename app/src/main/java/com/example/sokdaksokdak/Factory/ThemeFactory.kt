package com.example.sokdaksokdak.Factory

import com.example.sokdaksokdak.CalendarFragment
import com.example.sokdaksokdak.DiaryFragment

interface ThemeFactory{

    fun createDiary():DiaryFragment
    fun createCalendar():CalendarFragment
}