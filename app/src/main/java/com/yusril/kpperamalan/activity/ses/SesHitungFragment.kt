package com.yusril.kpperamalan.activity.ses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.adapter.ListHasilSesAdapter
import com.yusril.kpperamalan.databinding.FragmentSesHitungBinding
import com.yusril.kpperamalan.viewmodel.SesViewModel

class SesHitungFragment : Fragment() {
    private lateinit var binding: FragmentSesHitungBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var sesViewModel: SesViewModel
    private lateinit var listHasilSesAdapter: ListHasilSesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ses_hitung, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sesViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SesViewModel::class.java)
        binding= FragmentSesHitungBinding.bind(view)
        recyclerView=binding.rvHasil
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(context)
        sesViewModel.setHasil()
        sesViewModel.getHasil().observe(viewLifecycleOwner,{datahasil->
            binding.progressBar.visibility=View.GONE
            listHasilSesAdapter= ListHasilSesAdapter(datahasil)
            recyclerView.adapter=listHasilSesAdapter
        })
    }
}