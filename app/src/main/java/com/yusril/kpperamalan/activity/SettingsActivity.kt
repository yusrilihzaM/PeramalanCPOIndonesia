package com.yusril.kpperamalan.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yusril.kpperamalan.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.title=getString(R.string.settings)
        supportFragmentManager.beginTransaction().add(R.id.setting_holder, SettingsPreferenceFragment()).commit()
    }
}