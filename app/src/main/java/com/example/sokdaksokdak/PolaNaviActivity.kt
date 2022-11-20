package com.example.sokdaksokdak

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sokdaksokdak.Factory.DefaultPreferenceManager
import com.example.sokdaksokdak.Login.LoginFragment
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.databinding.ActivityPolaNaviBinding

private const val TAG_KEYWORD = "keyword_fragment"
private const val TAG_CALENDAR ="calender_fragment"
private const val TAG_DIARY = "diary_fragment"
private const val TAG_LOGIN = "login_fragment"
private const val TAG_MY_PAGE = "mypage_fragment"
private const val TAG_THEME = "theme_change_fragment"

class PolaNaviActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPolaNaviBinding
    private lateinit var db : AppDatabase
    var currentTheme = R.style.AppPolaTheme
    private lateinit var pref: DefaultPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pref = DefaultPreferenceManager(this)

        val theme = pref.getThemeType()
        currentTheme = getAppTheme(theme)
        setTheme(currentTheme)

        binding = ActivityPolaNaviBinding.inflate(layoutInflater)


        db = AppDatabase.getInstance(applicationContext)!!


        setFragment(TAG_LOGIN, LoginFragment())
        binding.navigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.calendarFragment->{
                    setFragment(TAG_CALENDAR, CalendarFragment())
                    true
                }
                R.id.diaryFragment->{
                    setFragment(TAG_DIARY,DiaryFragment())
                    true
                }
                R.id.mypageFragment->{
//                    setFragment(TAG_THEME,ThemeChangeFragment())

                    val intent = Intent(this,ThemeChangeActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }

        }
        setContentView(binding.root)
    }

    override fun onResume() {

        val themeType = pref.getThemeType()
        val settingTheme = getAppTheme(themeType)

        if (currentTheme != settingTheme) {
            recreate()
        }
            super.onResume()
    }

    fun getAppTheme(theme: String?): Int {
        var newTheme: Int
        when (theme) {
            "pola_theme" -> newTheme = R.style.AppPolaTheme
            "clover_theme" -> newTheme = R.style.AppCloverTheme
            else -> newTheme = R.style.AppPolaTheme
        }
        return newTheme

    }


    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }


        val keyword = manager.findFragmentByTag(TAG_KEYWORD)
        val calendar = manager.findFragmentByTag(TAG_CALENDAR)
        val diary = manager.findFragmentByTag(TAG_DIARY)
        val mypage = manager.findFragmentByTag(TAG_MY_PAGE)
        val login = manager.findFragmentByTag(TAG_LOGIN)
        val theme = manager.findFragmentByTag(TAG_THEME)

        if (calendar != null) {
            fragTransaction.hide(calendar)
        }

        if (diary != null) {
            fragTransaction.hide(diary)
        }

        if (mypage != null) {
            fragTransaction.hide(mypage)
        }
        if (theme != null) {
            fragTransaction.hide(theme)
        }

        if (tag == TAG_CALENDAR) {
            if (calendar != null) {
                fragTransaction.show(calendar)
            }
        } else if (tag == TAG_DIARY) {
            if (diary != null) {
                fragTransaction.show(diary)
            }
        } else if (tag == TAG_MY_PAGE) {
            if (mypage != null) {
                fragTransaction.show(mypage)
            }
        } else if (tag == TAG_KEYWORD) {
            if (keyword != null) {
                fragTransaction.show(keyword)
            }
        } else if (tag == TAG_LOGIN) {
            if (login != null) {
                fragTransaction.show(login)
            }
        } else if (tag == TAG_THEME) {
            if (theme != null) {
                fragTransaction.show(theme)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

    fun receiveData(theme: String?) {
        println(theme)
        onResume()
    }
}

