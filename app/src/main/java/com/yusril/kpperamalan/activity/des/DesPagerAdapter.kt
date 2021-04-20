package com.yusril.kpperamalan.activity.des

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DesPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? =null
            when (position) {
                0 -> fragment = DesEvaluasiFragment()
                1 -> fragment = DesHitungFragment()
                2 -> fragment = DesRamalFragment()
                3 -> fragment = DesChartFragment()
        }
        return fragment as Fragment
    }
}