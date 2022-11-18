package com.example.sokdaksokdak

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary
import com.example.sokdaksokdak.database.User
import com.example.sokdaksokdak.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        refreshLogin()
        refreshDiary()

        binding.button.setOnClickListener {
            //addDataDiary()
            refreshLogin()
            refreshDiary()
            val intent = Intent(this, PolaSplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }

    }

    private fun addData() {
        val username = "이태혁"
        val userbirth = 201811246

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(User(username, userbirth))
        }
    }
    private fun deleteData() {
        val username = "이태혁"
        val userbirth = 201811246

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().delete(User(username, userbirth))
        }
    }

    private fun addDataDiary() {
        val keyword = "음식"
        val date = 20221118
        val diaryContext = "오늘은 사과를 먹었다. 맛있었다."

        CoroutineScope(Dispatchers.IO).launch {
            db.diaryDao().insertDiary(Diary(keyword, date, diaryContext))
        }
    }

    private fun deleteDataDiary() {
        val keyword = "음식"
        val date = 20221117
        val diaryContext = "오늘은 사과를 먹었다. 맛있었다."

        CoroutineScope(Dispatchers.IO).launch {
            db.diaryDao().delete(Diary(keyword, date, diaryContext))
        }
    }

    private fun refreshLogin() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                db.userDao().getname("이태혁")
            }.await()

            if (data != null) {
//                binding.nameText.setText("${data.userName}")
//                binding.birthText.setText("${data.birth}")
            }
        }
    }

    private fun refreshDiary() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async {
                db.diaryDao().getDiary()
            }.await()

            if (data != null) {
//                binding.keywordText.setText("${data.keyword}")
//                binding.dateText.setText("${data.date}")
//                binding.contextText.setText("${data.diaryContext}")
            }
        }
    }
}


