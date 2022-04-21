package com.madchan.supportandroid12.exactAlarmPermission

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.madchan.supportandroid12.databinding.ActivityExactAlarmPermissionBinding
import java.util.*

class ExactAlarmPermissionActivity : AppCompatActivity() {

    lateinit var binding: ActivityExactAlarmPermissionBinding

    private val stateMachineLD = MutableLiveData(ExactAlarmPermissionStateMachine.current())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExactAlarmPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addStateMachineObserver()
        
        if(stateMachineLD.value != ExactAlarmPermissionStateMachine.START) {
            stateMachineLD.value = stateMachineLD.value?.nextState()
        }
    }

    override fun onRestart() {
        super.onRestart()
        stateMachineLD.value = stateMachineLD.value?.nextState()
    }

    private fun addStateMachineObserver() {
        stateMachineLD.observe(this) {
            binding.textView.text =
                when (it) {
                    ExactAlarmPermissionStateMachine.START -> "点击任一【以XXX方式设置闹钟】后，应用会崩溃，请关注Logcat的Error级别信息，看是否显示了一下消息：\\nCaused by: java.lang.SecurityException: Caller com.madchan.supportandroid12 needs to hold android.permission.SCHEDULE_EXACT_ALARM to set exact alarms."
                    ExactAlarmPermissionStateMachine.CRASH_FOR_NO_DECLARE_PERMISSION -> "下一步，请将AndroidManifest.xml中被注释的SCHEDULE_EXACT_ALARM权限声明恢复后，重新运行，并重新点击。"
                    ExactAlarmPermissionStateMachine.DECLARED_PERMISSION -> "现在，请重新点击【以XXX方式设置闹钟】，验证应用是否还会崩溃"
                    ExactAlarmPermissionStateMachine.READY_TO_FORBIT_PERMISSION -> "恭喜成功，接下来，请点击【打开闹钟和提醒权限授权页面】关闭本应用的权限，模拟用户主动关闭权限的行为"
                    ExactAlarmPermissionStateMachine.FORBITED_PERMISSION -> "此时，如果再次点击【以XXX方式设置闹钟】，应用还是会崩溃"
                    ExactAlarmPermissionStateMachine.READY_TO_REQUIRE_PERMISSION -> "下一步，请点击【打开闹钟和提醒权限授权页面】重新开启本应用的权限"
                    ExactAlarmPermissionStateMachine.REQUIRED_PERMISSION -> "现在，请重新点击【以XXX方式设置闹钟】，验证应用是否还会崩溃"
                    ExactAlarmPermissionStateMachine.END -> "验证结束，如Demo演示有问题，可上GitHub上提issue，谢谢～"
                }
            ExactAlarmPermissionStateMachine.save(it)
        }
    }

    fun alarmBySetAlarmClock(view: View) {
        stateMachineLD.value = stateMachineLD.value?.nextState()

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val alarmIntent = Intent(this, ExactAlarmPermissionReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        alarmManager?.setAlarmClock(
            AlarmClockInfo(Date().time + 1000, alarmIntent),
            alarmIntent
        )
    }

    fun alarmBySetExact(view: View) {
        stateMachineLD.value = stateMachineLD.value?.nextState()

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val alarmIntent = Intent(this, ExactAlarmPermissionReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        alarmManager?.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + 1000, alarmIntent
        )
    }

    fun alarmBySetExactAndAllowWhileIdle(view: View) {
        stateMachineLD.value = stateMachineLD.value?.nextState()

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val alarmIntent = Intent(this, ExactAlarmPermissionReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager?.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1000, alarmIntent
            )
        }
    }

    fun requireAlarmPermission(view: View) {
        val uri = Uri.parse("package:$packageName")
        val i = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM, uri)
        startActivity(i)
    }

   

}