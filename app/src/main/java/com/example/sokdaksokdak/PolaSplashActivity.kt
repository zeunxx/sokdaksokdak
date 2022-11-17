package com.example.sokdaksokdak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.Diary
import com.example.sokdaksokdak.database.User
import com.example.sokdaksokdak.databinding.ActivityPolaSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.Duration


class PolaSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pola_splash)
//

        Handler().postDelayed({
            val intent = Intent(this, PolaNaviActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },Duration)

    }

    companion object{
        private const val Duration:Long=3000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}