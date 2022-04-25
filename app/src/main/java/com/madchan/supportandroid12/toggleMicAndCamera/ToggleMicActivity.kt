package com.madchan.supportandroid12.toggleMicAndCamera

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.madchan.supportandroid12.databinding.ActivityToggleMicBinding
import java.io.File


class ToggleMicActivity : AppCompatActivity() {

    lateinit var binding: ActivityToggleMicBinding
    var mediaRecorder: MediaRecorder? = null

    var audioFile: File? = null
    var startTimes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToggleMicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }                           

    fun start(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mediaRecorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS)
                    audioFile = File(cacheDir, "test.mp3")
                    setOutputFile(audioFile)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

                    prepare()
                    start()

                    startTimes++
                }
                when {
                    startTimes == 1 -> {
                        binding.textView.text = "现在，请在状态栏下拉打开快捷设置菜单，关闭麦克风开关。\n\n若没找到，请点击快捷设置菜单右上角编辑按钮，将麦克风开关拖拽到菜单中。"
                        Handler(mainLooper).postDelayed({
                            binding.textView.text = "若已关闭麦克风开关，状态栏中的标志应会消失。\n\n此时录音仍在持续，但输出会变为一段无声音频，可停止录音并聆听来验证。"
                        }, 10 * 1000L)
                    }
                    startTimes > 1 ->  binding.textView.text = "验证结束，如Demo演示有问题，可上GitHub上提issue，谢谢～"
                }
            }
        }
    }

    fun stop(view: View) {
        mediaRecorder?.stop()
        mediaRecorder?.release()

        binding.textView.text = "录音已停止，请选择合适应用验证录音的后半段是否变为静音。\n\n随后请重新开始录音，验证系统是否会重新提示开启麦克风开关。"

        audioFile?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intent.setDataAndType(FileProvider.getUriForFile(this, "$packageName.fileprovider",it), "audio/*")
            startActivity(intent)
        }

    }

    override fun finish() {
        stop(binding.stopBtn)
        super.finish()
    }

}