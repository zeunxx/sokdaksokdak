package com.example.sokdaksokdak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
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
    var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = DefaultPreferenceManager(this)

        val theme = pref.getThemeType()
        currentTheme = getAppTheme(theme)
        setTheme(currentTheme)

        setContentView(R.layout.activity_pola_splash)

        Handler().postDelayed({
            val intent = Intent(this, PolaNaviActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },Duration)

    }

    override fun onResume() {

        val themeType=pref.getThemeType()
        val settingTheme = getAppTheme(themeType)
        if(currentTheme!=settingTheme){
            recreate()
        }
        super.onResume()
    }

    private fun getAppTheme(theme: String?): Int {
        var newTheme:Int
        when (theme) {
            "pola_theme" -> newTheme = R.style.AppPolaTheme
            "clover_theme" ->newTheme =  R.style.AppCloverTheme
            else -> newTheme = R.style.AppPolaTheme
        }
        return newTheme

    }
    companion object{
        private const val Duration:Long=1000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}