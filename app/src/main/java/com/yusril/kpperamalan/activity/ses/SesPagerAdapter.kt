package com.yusril.kpperamalan.activity.ses

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yusril.kpperamalan.activity.des.DesChartFragment
import com.yusril.kpperamalan.activity.des.DesEvaluasiFragment
import com.yusril.kpperamalan.activity.des.DesHitungFragment
import com.yusril.kpperamalan.activity.des.DesRamalFragment

class SesPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? =null
        when (position) {
            0 -> fragment = SesEvaluasiFragment()
            1 -> fragment = SesHitungFragment()
            2 -> fragment = SesRamalFragment()
            3 -> fragment = SesChartFragment()
        }
        return fragment as Fragment
    }
}