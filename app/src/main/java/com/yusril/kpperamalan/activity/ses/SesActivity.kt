package com.yusril.kpperamalan.activity.ses

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.ActivitySesBinding

class SesActivity : AppCompatActivity() {
    companion object {
        @StringRes
        private  val TAB_TITLES= intArrayOf(
            R.string.evaluasi,
            R.string.perhitungan,
            R.string.ramal,
            R.string.chart
        )
    }
    private lateinit var binding:ActivitySesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ses)
        supportActionBar?.title=getString(R.string.Single_Exponential_Smoothing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivitySesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sesPagerAdapter= SesPagerAdapter(this)
        val viewPager=binding.viewPager
        viewPager.adapter=sesPagerAdapter
        val tabs=binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position->
            tab.text=resources.getString(TAB_TITLES[position])
        }.attach()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }
}