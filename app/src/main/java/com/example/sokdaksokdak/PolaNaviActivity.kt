package com.example.sokdaksokdak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
    private var themeType:Int = 2 // 처음 테마 값
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPolaNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!

        setFragment(TAG_LOGIN, LoginFragment())
        binding.navigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.calendarFragment->{
                    setFragment(TAG_CALENDAR, CalendarFragment(themeType))
                    true
                }
                R.id.diaryFragment->{
                    setFragment(TAG_DIARY,DiaryFragment(themeType))
                    true
                }
                R.id.mypageFragment->{
                    setFragment(TAG_THEME,ThemeChangeFragment())
                    true
                }
                else -> false
            }

        }
    }


    private fun setFragment(tag:String, fragment: Fragment){
        val manager:FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        fragTransaction.add(R.id.mainFrameLayout,fragment,tag)
//        if(manager.findFragmentByTag(tag)==null){
//            fragTransaction.add(R.id.mainFrameLayout,fragment,tag)
//        }


        val keyword = manager.findFragmentByTag(TAG_KEYWORD)
        val calendar = manager.findFragmentByTag(TAG_CALENDAR)
        val diary = manager.findFragmentByTag(TAG_DIARY)
        val mypage = manager.findFragmentByTag(TAG_MY_PAGE)
        val login = manager.findFragmentByTag(TAG_LOGIN)
        val theme = manager.findFragmentByTag(TAG_THEME)

        if (calendar != null){
            fragTransaction.hide(calendar)
        }

        if (diary != null){
            fragTransaction.hide(diary)
        }

        if (mypage != null) {
            fragTransaction.hide(mypage)
        }
        if (theme != null) {
            fragTransaction.hide(theme)
        }

        if (tag == TAG_CALENDAR) {
            if (calendar!=null){
                fragTransaction.show(calendar)
            }
        }
        else if (tag == TAG_DIARY) {
            if (diary != null) {
                fragTransaction.show(diary)
            }
        }

        else if (tag == TAG_MY_PAGE){
            if (mypage != null){
                fragTransaction.show(mypage)
            }
        }

        else if(tag== TAG_KEYWORD){
            if(keyword!=null){
                fragTransaction.show(keyword)
            }
        }

        else if(tag== TAG_LOGIN){
            if(login!=null){
                fragTransaction.show(login)
            }
        }

        else if(tag== TAG_THEME){
            if(theme!=null){
                fragTransaction.show(theme)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

    fun receiveData(diary:DiaryFragment, calendar:CalendarFragment,diaryTheme: Int){

        this.themeType = diaryTheme
        setFragment(TAG_DIARY,diary)
        setFragment(TAG_CALENDAR,calendar)
//        setFragment(TAG_THEME,ThemeChangeFragment())


//        val manager:FragmentManager = supportFragmentManager
//        val fragTransaction = manager.beginTransaction()
//
//        fragTransaction.add(R.id.mainFrameLayout,diary, TAG_DIARY)
//        fragTransaction.commit()
    }
}