package com.yusril.kpperamalan.activity.tentang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.activity.ses.SesPagerAdapter
import com.yusril.kpperamalan.databinding.ActivitySesBinding
import com.yusril.kpperamalan.databinding.ActivityTentangBinding

class TentangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTentangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang)
        binding = ActivityTentangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tentangViewPager= TentangViewPager(this)
        val viewPager=binding.viewPager
        viewPager.adapter=tentangViewPager
        viewPager.setPageTransformer(DepthPageTransformer())
    }

}
private const val MIN_SCALE = 0.75f

@RequiresApi(21)
class DepthPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                    translationX = 0f
                    translationZ = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1 -> {
                    alpha = 1 - position
                    translationX = pageWidth * -position
                    translationZ = -1f
                    val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}