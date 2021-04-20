package com.yusril.kpperamalan.activity.des

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.adapter.ListHasilDesAdapter
import com.yusril.kpperamalan.adapter.ListHasilSesAdapter
import com.yusril.kpperamalan.databinding.FragmentDesHitungBinding
import com.yusril.kpperamalan.databinding.FragmentSesHitungBinding
import com.yusril.kpperamalan.viewmodel.DesViewModel
import com.yusril.kpperamalan.viewmodel.SesViewModel


class DesHitungFragment : Fragment() {
    private lateinit var binding: FragmentDesHitungBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var desViewModel: DesViewModel
    private lateinit var listHasilDesAdapter: ListHasilDesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_des_hitung, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        desViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DesViewModel::class.java)
        binding= FragmentDesHitungBinding.bind(view)
        recyclerView=binding.rvHasil
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(context)
        desViewModel.setHasil()
        desViewModel.getHasil().observe(viewLifecycleOwner,{datahasil->
            binding.progressBar.visibility=View.GONE
            listHasilDesAdapter= ListHasilDesAdapter(datahasil)
            recyclerView.adapter=listHasilDesAdapter
        })
    }
}