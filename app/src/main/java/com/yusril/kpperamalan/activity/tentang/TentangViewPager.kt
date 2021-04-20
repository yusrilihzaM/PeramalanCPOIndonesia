package com.yusril.kpperamalan.activity.tentang

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yusril.kpperamalan.activity.ses.SesChartFragment
import com.yusril.kpperamalan.activity.ses.SesEvaluasiFragment
import com.yusril.kpperamalan.activity.ses.SesHitungFragment
import com.yusril.kpperamalan.activity.ses.SesRamalFragment

class TentangViewPager(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? =null
        when (position) {
            0 -> fragment = AboutFragment()
        }
        return fragment as Fragment
    }
}