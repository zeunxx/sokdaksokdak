package com.example.sokdaksokdak.Factory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sokdaksokdak.*
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.google.android.material.internal.ContextUtils.getActivity

class CloverThemeFactory() : ThemeFactory {
    override fun createNaviActivity(context: Context) {
        val intent = Intent(context, PolaNaviActivity::class.java)
        context.startActivity(intent)
    }

    override fun createSplashActivity(context: Context){
        val intent = Intent(context, PolaSplashActivity::class.java)
        context.startActivity(intent)
    }

}