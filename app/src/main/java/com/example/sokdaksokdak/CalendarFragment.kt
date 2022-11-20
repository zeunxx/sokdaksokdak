package com.example.sokdaksokdak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.databinding.FragmentCalendarBinding



class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentCalendarBinding.inflate(layoutInflater)
        return binding!!.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}