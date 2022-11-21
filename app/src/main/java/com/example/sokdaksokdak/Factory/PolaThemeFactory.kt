package com.example.sokdaksokdak.Factory

import com.example.sokdaksokdak.*

class PolaThemeFactory(override val activity: Any) :ThemeFactory {
    override fun createNaviActivity() {
        activity as PolaNaviActivity

    }

    override fun createSplashActivity() {
        activity as PolaSplashActivity
        activity.recreate()

    }
}