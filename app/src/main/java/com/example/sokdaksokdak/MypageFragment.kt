package com.example.sokdaksokdak

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.example.sokdaksokdak.databinding.FragmentMypageBinding


class MypageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = FragmentMypageBinding.inflate(layoutInflater)
        binding.themeBtn.setOnClickListener {
            val intent = Intent(activity,ThemeChangeActivity::class.java)
            startActivity(intent)

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}