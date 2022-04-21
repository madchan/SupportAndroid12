package com.madchan.supportandroid12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.madchan.supportandroid12.activity.AppStartupActivity
import com.madchan.supportandroid12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun toggleMic(view: View) {
        startActivity(Intent(this, ToggleMicActivity::class.java))
    }

    fun foregroundServiceLimit(view: View) {
        startActivity(Intent(this, ForegroundServiceLimitActivity::class.java))
        finish()
    }

    fun notificationTrampolineLimit(view: View) {
        startActivity(Intent(this, NotificationTrampolineLimitActivity::class.java))
        finish()
    }

    fun approximateLocation(view: View) {
        startActivity(Intent(this, ApproximateLocationActivity::class.java))
    }

    fun customNotification(view: View) {
        startActivity(Intent(this, CustomNotificationActivity::class.java))
    }

    fun exactAlarmPermission(view: View) {
        startActivity(Intent(this, ExactAlarmPermissionActivity::class.java))
    }

    fun appStartup(view: View) {
        startActivity(Intent(this, AppStartupActivity::class.java))
    }
}