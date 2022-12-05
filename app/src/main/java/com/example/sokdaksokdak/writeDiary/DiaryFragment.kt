package com.example.sokdaksokdak.writeDiary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        savedInstanceState: Bundle?,
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

        // 사용자가 keyword 를 수정했을 때
        // 실시간 DB 반영


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
            // 작성 완료 버튼 없는 것으로 취급
            binding.diaryDoneBtn.visibility = View.GONE

            // 일기 작성 칸 TextView 로 데이터 표시만. EditText 는 없는 것으로 취급
            binding.diaryTextView.visibility = View.VISIBLE
            binding.diaryEditText.visibility = View.GONE
            binding.diaryTextView.setText(writeDiaryViewModel.showContent())

            binding.keywordEditView.visibility = View.GONE
            binding.keywordTextView.visibility = View.VISIBLE
            binding.keywordTextView.setText(writeDiaryViewModel.showKeyword())
        } else {
            // showKeyword
            binding.keywordEditView.setText(writeDiaryViewModel.showKeyword())

            // TODO: 추천 키워드를 화면에 표시하는 것까지는 clear.
            //       -> 사용자가 keyword 를 수정(또는 새로 입력)했을 때
            binding.keywordEditView.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int,
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    val keyword = s.toString()
                    writeDiaryViewModel.setKeyword(keyword)
                }
            })

            // newDiaryData
            /**
             * 사용자가 일기 작성 완료 버튼 눌렀을 때 - DB update
             *
             * */
            binding.diaryDoneBtn.setOnClickListener {
                val keyword = binding.keywordEditView.text.toString()
                val content = binding.diaryEditText.text.toString()

                writeDiaryViewModel.newDiaryData(keyword, content)

                // 일기 작성 완료 후
                // 버튼 없애기
                // keyword, content: EditView -> TextView 변경
                binding.diaryDoneBtn.visibility = View.GONE

                binding.diaryTextView.visibility = View.VISIBLE
                binding.diaryEditText.visibility = View.GONE
                binding.diaryTextView.setText(content)

                binding.keywordEditView.visibility = View.GONE
                binding.keywordTextView.visibility = View.VISIBLE
                binding.keywordTextView.setText(writeDiaryViewModel.showKeyword())


            }

        }

        return binding.root
    }

}