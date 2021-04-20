package com.yusril.kpperamalan.activity.tentang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentMhsBinding
import com.yusril.kpperamalan.databinding.FragmentSesChartBinding


class MhsFragment : Fragment() {
    private lateinit var binding: FragmentMhsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mhs, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMhsBinding.bind(view)

    }
}