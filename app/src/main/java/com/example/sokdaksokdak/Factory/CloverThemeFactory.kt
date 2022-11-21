package com.example.sokdaksokdak.Factory

import android.app.Activity
import android.os.Bundle
import com.example.sokdaksokdak.*
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.google.android.material.internal.ContextUtils.getActivity

class CloverThemeFactory(override val activity: Any) : ThemeFactory {
    override fun createNaviActivity() {
        activity as PolaNaviActivity
        activity.recreate()
    }

    override fun createSplashActivity() {
        activity as PolaNaviActivity
        activity.recreate()
    }

}