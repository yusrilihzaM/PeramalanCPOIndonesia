package com.yusril.kpperamalan.activity.des

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.activity.dataminyak.AddDataActivity
import com.yusril.kpperamalan.databinding.ActivityDesBinding

class DesActivity : AppCompatActivity() {
    companion object {
        @StringRes
        private  val TAB_TITLES= intArrayOf(
            R.string.evaluasi,
            R.string.perhitungan,
            R.string.ramal,
            R.string.chart
        )
    }
    private lateinit var binding: ActivityDesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_des)

        supportActionBar?.title=getString(R.string.Double_Exponential_Smoothing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityDesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val desPagerAdapter=DesPagerAdapter(this)
        val viewPager=binding.viewPager
        viewPager.adapter=desPagerAdapter
        val tabs=binding.tabs
        TabLayoutMediator(tabs, viewPager){ tab, position->
            tab.text=resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.data_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                startActivity(Intent(this, InputRamalActivity::class.java))
                true
            }
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }
}