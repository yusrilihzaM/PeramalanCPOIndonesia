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
import com.yusril.kpperamalan.adapter.ListRamalDesAdapter
import com.yusril.kpperamalan.databinding.FragmentDesHitungBinding
import com.yusril.kpperamalan.databinding.FragmentDesRamalBinding
import com.yusril.kpperamalan.viewmodel.DesViewModel


class DesRamalFragment : Fragment() {
    private lateinit var binding: FragmentDesRamalBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var desViewModel: DesViewModel
    private lateinit var listRamalDesAdapter: ListRamalDesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_des_ramal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        desViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DesViewModel::class.java)
        binding= FragmentDesRamalBinding.bind(view)
        recyclerView=binding.rvHasil
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(context)
        desViewModel.setRamalList()
        desViewModel.getRamal().observe(viewLifecycleOwner,{dataRamal->
            binding.progressBar.visibility=View.GONE
            listRamalDesAdapter= ListRamalDesAdapter(dataRamal)
            recyclerView.adapter=listRamalDesAdapter
        })
    }
}