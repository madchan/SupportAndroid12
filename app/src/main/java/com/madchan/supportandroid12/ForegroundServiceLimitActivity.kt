package com.madchan.supportandroid12

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ForegroundServiceLimitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_limit_foreground_service)
    }

    fun startForegroundService(view: View) {
        moveTaskToBack(false)
        Handler(mainLooper).postDelayed({
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val intent = Intent(this, ForegroundServiceLimitService::class.java) // Build the intent for the service
                startForegroundService(intent)
            }
        }, 10000)
    }
}