package com.madchan.supportandroid12

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificationTrampolineLimitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_trampoline_limit)
    }

    fun notificationToStartService(view: View) {
        moveTaskToBack(false)

        val pendingIntent: PendingIntent =
            Intent(this, NotificationTrampolineLimitService::class.java).let { notificationIntent ->
                PendingIntent.getService(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("CHANNEL_ID", "name", NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = "descriptionText"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("notificationToStartService")
                .setContentText("ContentText")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            notificationManager.notify(0, notification)
        }
    }

    fun notificationToStartBroadcastReceiver(view: View) {
        moveTaskToBack(false)

        val pendingIntent: PendingIntent =
            Intent(this, NotificationTrampolineLimitReceiver::class.java).let { notificationIntent ->
                PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("CHANNEL_ID", "name", NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = "descriptionText"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("notificationToStartBroadcastReceiver")
                .setContentText("ContentText")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            notificationManager.notify(1, notification)
        }
    }

}