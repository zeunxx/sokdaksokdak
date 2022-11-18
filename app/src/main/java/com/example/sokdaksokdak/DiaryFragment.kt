package com.example.sokdaksokdak

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.example.sokdaksokdak.databinding.FragmentDiaryCloverBinding
import kotlin.properties.Delegates

class DiaryFragment(themeType:Int) : Fragment() {

    var themeType = themeType


//    private var polaBinding : FragmentDiaryBinding ?= null
//    private var cloverBinding : FragmentDiaryCloverBinding ?= null


    companion object {
        fun newInstance(theme: Int): DiaryFragment {
            val fragment = DiaryFragment(theme)
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
            var binding = FragmentDiaryBinding.inflate(layoutInflater)
            return binding!!.root
        }
        else
        {
            var binding = FragmentDiaryCloverBinding.inflate(layoutInflater)
            return binding!!.root
        }
    }



        override fun onDestroyView() {
//        polaBinding = null
//        cloverBinding = null

            super.onDestroyView()
        }
}