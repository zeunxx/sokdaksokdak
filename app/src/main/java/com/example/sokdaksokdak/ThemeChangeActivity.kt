package com.example.sokdaksokdak

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sokdaksokdak.Factory.CloverThemeFactory
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.Factory.PolaThemeFactory
import com.example.sokdaksokdak.Factory.ThemeFactory
import com.example.sokdaksokdak.databinding.ActivityPolaNaviBinding
import com.example.sokdaksokdak.databinding.ActivityThemeChangeBinding
import com.example.sokdaksokdak.ui.main.ThemeChangeFragment2

class ThemeChangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThemeChangeBinding
    private var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager
    companion object{
        private const val TAG = "PolaNaviActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeChangeBinding.inflate(layoutInflater)
        pref= DefaultPreferenceManager(this)

        binding.themeRadioGrp.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.pola_theme_radioBtn->{
                    pref.setThemeType("pola_theme")
                    PolaThemeFactory().createNaviActivity(this)
                    PolaThemeFactory().createSplashActivity(this)

                }
                R.id.clover_theme_radioBtn->{
                    pref.setThemeType("clover_theme")
                    CloverThemeFactory().createNaviActivity(this)
                    CloverThemeFactory().createSplashActivity(this)

                }
            }
        }


        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ThemeChangeFragment2.newInstance())
                .commitNow()
        }
    }
}