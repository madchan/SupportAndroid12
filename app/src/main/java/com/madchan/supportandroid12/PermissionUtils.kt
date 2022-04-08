package com.madchan.supportandroid12

import android.app.AlarmManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

fun hasRequirePermission(context: Context): Boolean {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        alarmManager?.canScheduleExactAlarms() == true
    } else {
        false
    }
}

fun hasDeclarePermission(context: Context): Boolean {
    val packageManager: PackageManager = context.packageManager
    try {
        val packageInfo =
            packageManager.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
        val permissions = packageInfo.requestedPermissions
        return permissions.contains(android.Manifest.permission.SCHEDULE_EXACT_ALARM)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return false
}

class PermissionUtils {
}