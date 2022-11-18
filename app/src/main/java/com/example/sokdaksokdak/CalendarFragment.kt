package com.example.sokdaksokdak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.databinding.FragmentCalendarBinding
import com.example.sokdaksokdak.databinding.FragmentCalendarCloverBinding
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.example.sokdaksokdak.databinding.FragmentDiaryCloverBinding


class CalendarFragment(themeType:Int) : Fragment() {

    var themeType=themeType
//    private var polaBinding : FragmentCalendarBinding?= null
//    private var cloverBinding : FragmentCalendarCloverBinding?= null
    companion object {
        fun newInstance(theme:Int): CalendarFragment{
            val fragment = CalendarFragment(theme)
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("##### 테마 ######:"+themeType)

        if(themeType==1)
        {
            var binding = FragmentCalendarBinding.inflate(layoutInflater)
            return binding!!.root
        }
        else
        {
            var binding = FragmentCalendarCloverBinding.inflate(layoutInflater)
            return binding!!.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}