package com.madchan.supportandroid12

import android.app.Application
import android.content.Context

class SupportAndroid12Application : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}