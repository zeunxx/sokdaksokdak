package com.example.sokdaksokdak.writeDiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding


class DiaryFragment : Fragment() {
    private lateinit var binding: FragmentDiaryBinding
    private lateinit var writeDiaryViewModel: WriteDiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiaryBinding.inflate(inflater, container, false)

        writeDiaryViewModel = ViewModelProvider(this).get(WriteDiaryViewModel::class.java)

        binding.keywordTextView.text = writeDiaryViewModel.showKeyword()
        // Inflate the layout for this fragment
        return binding.root
    }

}