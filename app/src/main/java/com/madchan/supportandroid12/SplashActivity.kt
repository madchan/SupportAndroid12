package com.madchan.supportandroid12

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.madchan.supportandroid12.appSplashScreens.AppSplashScreensActivity
import com.madchan.supportandroid12.appSplashScreens.AppStartupStateMachine
import com.madchan.supportandroid12.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val outValue = TypedValue()
        theme.resolveAttribute(androidx.core.splashscreen.R.attr.windowSplashScreenAnimatedIcon, outValue, true)
        if(R.color.transparent == outValue.resourceId) {
            AppStartupStateMachine.save(AppStartupStateMachine.current().nextState())
        } else {
            // 验证常规方案请把以下一行取消注释，这样的话系统启动画面会持续覆盖原有的闪屏页，
            // 直到开始显示广告页
//            splashScreen.setKeepOnScreenCondition {
//                AppStartupStateMachine.save(AppStartupStateMachine.current().nextState())
//                true
//            }
        }

        Handler(mainLooper).postDelayed({
            splashScreen.setKeepOnScreenCondition { false }
            binding.root.setBackgroundColor(resources.getColor(R.color.teal_700))
            binding.textView.text = "一个无情无义的广告页"
        }, 2000)

        Handler(mainLooper).postDelayed({

            if(AppStartupStateMachine.END == AppStartupStateMachine.current()){
                startActivity(Intent(this, AppSplashScreensActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }

            finish()
        }, 4000)
    }
}