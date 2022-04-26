package com.madchan.supportandroid12.appSplashScreens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.madchan.supportandroid12.MainActivity
import com.madchan.supportandroid12.databinding.ActivityAppSplashScreensBinding

class AppSplashScreensActivity : AppCompatActivity() {

    lateinit var binding: ActivityAppSplashScreensBinding

    private val stateMachineLD = MutableLiveData(AppStartupStateMachine.current())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppSplashScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addStateMachineObserver()
    }

    private fun addStateMachineObserver() {
        stateMachineLD.observe(this) {
            binding.textView.text =
                when (it) {
                    AppStartupStateMachine.START -> "如你所见，从Android 12开始，系统为我们的App加上了一个默认的启动画面，该启动画面默认情况下包含windowBackground所设置的主题色和Launcher图标这2个元素。\n\n接下来，有两种适配的方案供你选择，你可请选择其中一种预览一下适配效果："
                    AppStartupStateMachine.PENDING_TO_SET_ICON_TRANSPARENT -> "懒人改变世界！这个方案很简单，请你把AndroidManifest中SplashActivity的主题改为Theme.App.Starting.Simple。\n\n该主题把默认启动画面中的图标改成了透明的颜色，因此只会显示背景色，是最简单的适配方案。改好后请重新运行。"
                    AppStartupStateMachine.PENDING_TO_USE_SPLASH_SCREEN_API -> "赞！敢于选择更困难的路，接下来，请你把AndroidManifest中SplashActivity的主题改回Theme.App.Starting.Normal(如果前面有改的话)，并将SplashActivity.kt中被注释的那条语句恢复，该语句会让默认启动画面覆盖原先的启动页，直到广告页才重新开始显示。改好后请重新运行。"
                    AppStartupStateMachine.END -> "验证结束，如Demo演示有问题，可上GitHub上提issue，谢谢～\n\n如果想预览另一个方案的效果，可继续选择另外一个方案。"
                }
            AppStartupStateMachine.save(it)
        }
    }

    fun pendingToSetIconTransparent(view: View) {
        stateMachineLD.value = stateMachineLD.value?.nextState(SCHEME_SET_ICON_TRANSPARENT)
    }

    fun pendingToUseSplashScreenApi(view: View) {
        stateMachineLD.value = stateMachineLD.value?.nextState(SCHEME_USE_SPLASH_SCREEN_API)
    }

    fun testOtherChange(view: View) {
        AppStartupStateMachine.clear()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}