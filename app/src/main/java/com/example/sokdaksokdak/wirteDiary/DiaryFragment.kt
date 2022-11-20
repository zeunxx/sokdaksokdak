package com.example.sokdaksokdak.writeDiary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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

        //binding.keywordTextView.text = writeDiaryViewModel.showKeyword()
        //writeDiaryViewModel.newDiaryData("변경 후 키워드", "변경 후 일기 내용")

        // TODO: 1. 사용자가 일기 작성 완료 버튼 눌렀을 때, DB update 되도록 - keyword 는 SharedPreference 에서 추출하여 전달
        // TODO: 2. SharedPreference 이용 -> 저장된 keyword 화면에 띄우기

        // TODO: ?? sharedPreference 이용해서 오늘 날짜에 저장된 keyword 가 있는지 추적


        // Inflate the layout for this fragment
        return binding.root
    }

}