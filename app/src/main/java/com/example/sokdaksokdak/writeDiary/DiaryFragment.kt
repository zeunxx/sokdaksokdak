package com.example.sokdaksokdak.writeDiary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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

        // TODO: 사용자의 keyword 추천 여부 반영 - 예외 처리

        // writeDiaryViewModel.deleteData()

        // showKeyword
        /**
         * 1. 오늘 날짜에 해당하는 일기 데이터가 존재하는지 확인
         *    1.1. 없는 경우 - 새로운 데이터 추가
         *    1.2. 있는 경우 - keyword 가져오기
         * 2. 가져온 keyword 가
         *    2.1. 초기 값일 때 - random 으로 키워드 가져오기
         *         2.1.1. 가져온 keyword DB 에 update
         *    2.2. 이미 갱신된 값일 때 - 바로 반환
         * 3. 화면에 keyword 표시
         * */
        binding.keywordTextView.text = writeDiaryViewModel.showKeyword()


        // newDiaryData
        /**
         * 사용자가 일기 작성 완료 버튼 눌렀을 때 - DB update
         *
         * */
        binding.diaryDoneBtn.setOnClickListener {
            val keyword = binding.keywordTextView.text.toString()
            val content = binding.diaryEditText.text.toString()

            writeDiaryViewModel.newDiaryData(keyword, content)

            // 일기 작성 완료 직후, 버튼 없애고 EditView -> TextView 변경
            binding.diaryDoneBtn.visibility = View.GONE
            binding.diaryTextView.visibility = View.VISIBLE
            binding.diaryEditText.visibility = View.GONE
            binding.diaryTextView.setText(content)


        }


        // Inflate the layout for this fragment
        return binding.root
    }

}