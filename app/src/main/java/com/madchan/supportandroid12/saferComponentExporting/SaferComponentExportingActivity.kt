package com.madchan.supportandroid12.saferComponentExporting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.madchan.supportandroid12.databinding.ActivitySaferComponentExportingBinding

class SaferComponentExportingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySaferComponentExportingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaferComponentExportingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}