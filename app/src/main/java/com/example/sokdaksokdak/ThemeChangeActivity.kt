package com.example.sokdaksokdak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sokdaksokdak.Factory.CloverThemeFactory
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.Factory.PolaThemeFactory
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
//                    PolaThemeFactory(PolaNaviActivity()).createNaviActivity()
//                    PolaThemeFactory(PolaSplashActivity()).createSplashActivity()
                    val intent = Intent(this,PolaNaviActivity::class.java)
                    startActivity(intent)
                }
                R.id.clover_theme_radioBtn->{
                    pref.setThemeType("clover_theme")
//                    CloverThemeFactory(PolaNaviActivity()).createNaviActivity()
//                    CloverThemeFactory(PolaSplashActivity()).createSplashActivity()
                    val intent = Intent(this,PolaNaviActivity::class.java)
                    startActivity(intent)
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