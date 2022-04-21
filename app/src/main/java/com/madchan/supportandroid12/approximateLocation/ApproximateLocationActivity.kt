package com.madchan.supportandroid12.approximateLocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.madchan.supportandroid12.databinding.ActivityApproximateLocationBinding

class ApproximateLocationActivity : AppCompatActivity() {

    lateinit var binding: ActivityApproximateLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApproximateLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun requestFineLocation(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 1
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        runOnUiThread {
                            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(this, "确切位置权限已获得", Toast.LENGTH_LONG).show()
                                binding.textView.text = "验证结束，如Demo演示有问题，可上GitHub上提issue，谢谢～"
                            } else {
                                Toast.makeText(this, "大致位置权限已获得", Toast.LENGTH_LONG).show()
                                binding.textView.text = "现在，请尝试再次点击【请求确切位置】，系统将弹出权限对话框请求升级到确切位置"
                            }
                        }
                    }
                } else {

                }
            }
        }
    }

}