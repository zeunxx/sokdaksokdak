package com.example.sokdaksokdak.Factory

import com.example.sokdaksokdak.CalendarFragment
import com.example.sokdaksokdak.DiaryFragment

class PolaThemeFactory:ThemeFactory {
    override fun createDiary(): DiaryFragment {
        val theme = 2
        val pola = DiaryFragment.newInstance(theme)
        return pola
    }

    override fun createCalendar(): CalendarFragment {
        val theme = 2
        val pola = CalendarFragment.newInstance(theme)
        return pola
    }
}