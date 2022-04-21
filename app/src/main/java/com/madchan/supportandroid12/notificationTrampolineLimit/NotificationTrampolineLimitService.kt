package com.madchan.supportandroid12.notificationTrampolineLimit

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.madchan.supportandroid12.MainActivity

class NotificationTrampolineLimitService : Service() {

    override fun onStartCommand(intent: Intent?, serviceFlags: Int, startId: Int): Int {
        startActivity(Intent(this, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK })
        return super.onStartCommand(intent, serviceFlags, startId)
    }

    override fun onBind(intent: Intent): IBinder? = null
}