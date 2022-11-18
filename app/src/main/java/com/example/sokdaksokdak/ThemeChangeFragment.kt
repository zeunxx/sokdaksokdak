package com.example.sokdaksokdak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.Factory.CloverThemeFactory
import com.example.sokdaksokdak.Factory.PolaThemeFactory
import com.example.sokdaksokdak.databinding.FragmentThemeChangeBinding


class ThemeChangeFragment : Fragment() {

    private lateinit var binding  : FragmentThemeChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        if(savedInstanceState!=null){
//            var radioIndex = savedInstanceState.getInt("radioIndex")
//        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThemeChangeBinding.inflate(layoutInflater)
//        binding.polaThemeRadioBtn.isChecked = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기가 client???

        binding.themeRadioGrp.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.pola_theme_radioBtn->{
                    val activity = activity as PolaNaviActivity

                    val cloverDiary = CloverThemeFactory().createDiary()
                    val cloverCalendar = CloverThemeFactory().createCalendar()
                    activity.receiveData(cloverDiary,cloverCalendar,1)
                }
                R.id.clover_theme_radioBtn->{
                    val activity = activity as PolaNaviActivity
                    val polaDiary = PolaThemeFactory().createDiary()
                    val polaCalendar = PolaThemeFactory().createCalendar()
                    activity.receiveData(polaDiary,polaCalendar,2)

                }
            }
        }
    }

}



