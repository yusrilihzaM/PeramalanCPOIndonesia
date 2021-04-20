package com.yusril.kpperamalan.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.yusril.kpperamalan.R

class SplashScreen : Activity() {
    companion object{
        const val TIME:Long=3000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({ moveActivity() }, TIME)
    }
    private fun moveActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}