package com.madchan.supportandroid12.customNotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.madchan.supportandroid12.R
import com.madchan.supportandroid12.databinding.ActivityCustomNotificationBinding

class CustomNotificationActivity : AppCompatActivity() {

    lateinit var binding: ActivityCustomNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    @SuppressLint("RemoteViewLayout")
    fun notifyCustomNotification(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("CHANNEL_ID", "name", NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = "descriptionText"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            // Get the layouts to use in the custom notification
            val notificationLayout = RemoteViews(packageName, R.layout.notification_small)
            val notificationLayoutExpanded = RemoteViews(packageName, R.layout.notification_large)

            // Apply the layouts to the notification
            val notification = NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded)
                .build()

            notificationManager.notify(1, notification)

            binding.textView.text = "验证结束，如Demo演示有问题，可上GitHub上提issue，谢谢～"
        }
    }

}