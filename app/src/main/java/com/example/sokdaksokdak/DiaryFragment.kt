package com.example.sokdaksokdak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding


class DiaryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDiaryBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onDestroyView() {

            super.onDestroyView()
        }


}