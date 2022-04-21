package com.madchan.supportandroid12.appStartup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.madchan.supportandroid12.databinding.ActivityAppStartupBinding

class AppStartupActivity : AppCompatActivity() {

    lateinit var binding: ActivityAppStartupBinding

    private val stateMachineLD = MutableLiveData(AppStartupStateMachine.current())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppStartupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addStateMachineObserver()
    }

    private fun addStateMachineObserver() {
        stateMachineLD.observe(this) {
            binding.textView.text =
                when (it) {
                    AppStartupStateMachine.START -> "如你所见，从Android 12开始，系统为我们的App加上了一个默认的启动画面。\n\n接下来，有两个适配的方案供你选择，请选择其中一个方案预览效果："
                    AppStartupStateMachine.PENDING_TO_SET_ICON_TRANSPARENT -> "懒人改变世界！这个方案很简单，请你把AndroidManifest中SplashActivity的主题改为Theme.App.Starting.Simple，然后再重新运行。"
                    AppStartupStateMachine.PENDING_TO_USE_SPLASH_SCREEN_API -> "赞！敢于选择更困难的路，接下来，请你把AndroidManifest中SplashActivity的主题改回Theme.App.Starting.Normal，并将SplashActivity.kt中被注释的那条语句恢复，然后再重新运行。"
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

}