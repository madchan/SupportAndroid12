package com.madchan.supportandroid12.foregroundServiceLimit

import android.app.*
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.madchan.supportandroid12.MainActivity

class ForegroundServiceLimitService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("CHANNEL_ID", "name", IMPORTANCE_HIGH)
            mChannel.description = "descriptionText"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setContentIntent(pendingIntent)
                .build()

            // Notification ID cannot be 0.
            startForeground(1, notification)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? = null
}