package com.example.sokdaksokdak.Factory

import android.os.Bundle
import com.example.sokdaksokdak.CalendarFragment
import com.example.sokdaksokdak.DiaryFragment
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding

class CloverThemeFactory: ThemeFactory {
    override fun createCalendar(): CalendarFragment {
        val theme = 1
        val clover = CalendarFragment.newInstance(theme)
        return clover
    }

    override fun createDiary() :DiaryFragment{
        val theme = 1
        val clover = DiaryFragment.newInstance(theme)
        return clover
    }
}