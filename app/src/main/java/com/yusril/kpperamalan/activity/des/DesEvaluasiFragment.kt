package com.yusril.kpperamalan.activity.des

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentDesEvaluasiBinding
import com.yusril.kpperamalan.databinding.FragmentSesEvaluasiBinding
import com.yusril.kpperamalan.viewmodel.DesViewModel
import com.yusril.kpperamalan.viewmodel.SesViewModel

class DesEvaluasiFragment : Fragment() {

    private lateinit var desViewModel: DesViewModel
    private lateinit var binding: FragmentDesEvaluasiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_des_evaluasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDesEvaluasiBinding.bind(view)
        desViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DesViewModel::class.java)
        desViewModel.run {
            setError()
            setMad()
            setMSE()
            setMAPE()
            setTS()
        }
        desViewModel.getError().observe(viewLifecycleOwner,{dataError->
            binding.tvError.text=dataError
        })
        desViewModel.getMad().observe(viewLifecycleOwner,{dataMad->
            binding.tvMad.text=dataMad
        })
        desViewModel.getMse().observe(viewLifecycleOwner,{dataMse->
            binding.tvMse.text=dataMse
        })
        desViewModel.getMape().observe(viewLifecycleOwner,{dataMape->
            binding.tvMape.text=dataMape
        })
        desViewModel.getTs().observe(viewLifecycleOwner,{dataTs->
            binding.tvTs.text=dataTs
        })
    }
}