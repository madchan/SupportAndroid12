package com.madchan.supportandroid12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationTrampolineLimitReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startActivity(Intent(context, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK })
    }
}