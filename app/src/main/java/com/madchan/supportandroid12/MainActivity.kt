package com.madchan.supportandroid12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.madchan.supportandroid12.appStartup.AppStartupActivity
import com.madchan.supportandroid12.approximateLocation.ApproximateLocationActivity
import com.madchan.supportandroid12.customNotification.CustomNotificationActivity
import com.madchan.supportandroid12.databinding.ActivityMainBinding
import com.madchan.supportandroid12.exactAlarmPermission.ExactAlarmPermissionActivity
import com.madchan.supportandroid12.foregroundServiceLimit.ForegroundServiceLimitActivity
import com.madchan.supportandroid12.notificationTrampolineLimit.NotificationTrampolineLimitActivity
import com.madchan.supportandroid12.saferComponentExporting.SaferComponentExportingActivity
import com.madchan.supportandroid12.toggleMicAndCamera.ToggleMicActivity

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

    fun saferComponentExporting(view: View) {
        startActivity(Intent(this, SaferComponentExportingActivity::class.java))
    }
}