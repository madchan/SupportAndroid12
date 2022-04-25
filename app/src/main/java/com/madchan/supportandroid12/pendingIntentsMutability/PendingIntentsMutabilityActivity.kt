package com.madchan.supportandroid12.pendingIntentsMutability

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.madchan.supportandroid12.MainActivity
import com.madchan.supportandroid12.databinding.ActivityPendingIntentsMutabilityBinding
import com.madchan.supportandroid12.databinding.ActivitySaferComponentExportingBinding

class PendingIntentsMutabilityActivity : AppCompatActivity() {

    lateinit var binding: ActivityPendingIntentsMutabilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingIntentsMutabilityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun pendingIntentsMutability(view: View) {
        val pendingIntent: PendingIntent =
            Intent(this, MainActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("CHANNEL_ID", "name", NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = "descriptionText"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val notification: Notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setContentIntent(pendingIntent)
                .build()

            notificationManager.notify(1, notification)
        }
    }
}