package com.madchan.supportandroid12.exactAlarmPermission

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExactAlarmPermissionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        Toast.makeText(context, "通过闹钟开启了广播", Toast.LENGTH_LONG).show()
    }

}