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

        // Diary Table 비우기 - 확인을 위함
        // writeDiaryViewModel.deleteData()

        // TODO: 사용자의 keyword 추천 여부 반영 - 예외 처리

        // 앱 실행 시, 현재 날짜에 대한 DB Data 존재 및 작성 완료 상태 확인
        // -> 존재하지 않을 때
        //    -> insertData
        //    => 존재할 때의 순서 시행
        // -> 존재
        //    -> keyword & 내용 모두 작성 완료된 상태인지 확인
        //    -> keyword 만 갱신 된 상태일 때
        // TODO: 사용자가 keyword 를 수정했을 때


        // checkDataExists
        /**
         * 1. 오늘 날짜에 해당하는 일기 데이터가 존재하는지 확인
         *    1.1. 없는 경우 - 새로운 데이터 추가
         * */
        if (!writeDiaryViewModel.checkDataExists()){ // 오늘의 Data 존재하지 않을 때
            writeDiaryViewModel.insertData()
        }


        // showKeyword
        /**
         * keyword & 내용 모두 작성 완료된 상태라면
         * EditText 가 아닌 TextView 로 화면에 표시
         * */
        if (writeDiaryViewModel.checkDiaryCompleted()){
            binding.diaryDoneBtn.visibility = View.GONE
            binding.diaryTextView.visibility = View.VISIBLE
            binding.diaryEditText.visibility = View.GONE
            binding.diaryTextView.setText(writeDiaryViewModel.showContent())
        }

        // showKeyword
        // TODO: 사용자가 keyword 수정/직접 입력했을 때
        /**
         * keyword 만 갱신 된 상태일 때
         *
         * 1. 오늘 날짜에 해당하는 keyword 가져오기
         * 2. 가져온 keyword 가
         *    2.1. 초기 값일 때 - random 으로 키워드 가져오기
         *         2.1.1. 가져온 keyword DB 에 update
         *    2.2. 이미 갱신된 값일 때 - 바로 반환
         * 3. 화면에 keyword 표시
         *
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

        return binding.root
    }

}