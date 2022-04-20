package com.madchan.supportandroid12

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
//        startActivity(Intent(this, ExactAlarmPermissionActivity::class.java))

        Runtime.getRuntime().exec("device_config put app_hibernation app_hibernation_enabled true\n")
        Runtime.getRuntime().exec("threshold=\$(adb shell device_config get permissions \\\n" +
                "  auto_revoke_unused_threshold_millis2)\n")
        Runtime.getRuntime().exec("device_config put permissions \\\n" +
                "  auto_revoke_unused_threshold_millis2 1000\n")
        Runtime.getRuntime().exec("am wait-for-broadcast-idle\n")
        Runtime.getRuntime().exec("cmd jobscheduler run -u 0 -f \\\n" +
                "  com.google.android.permissioncontroller 2\n")
        Runtime.getRuntime().exec("cmd app_hibernation get-state ${packageName}\n")
    }
}