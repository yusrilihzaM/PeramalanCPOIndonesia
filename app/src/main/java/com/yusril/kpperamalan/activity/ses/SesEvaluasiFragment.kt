package com.yusril.kpperamalan.activity.ses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentSesEvaluasiBinding
import com.yusril.kpperamalan.viewmodel.SesViewModel


class SesEvaluasiFragment : Fragment() {

    private lateinit var sesViewModel: SesViewModel
    private lateinit var binding: FragmentSesEvaluasiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ses_evaluasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSesEvaluasiBinding.bind(view)
        sesViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SesViewModel::class.java)
        sesViewModel.run {
            setError()
            setMad()
            setMSE()
            setMAPE()
            setTS()
        }
        sesViewModel.getError().observe(viewLifecycleOwner,{dataError->
            binding.tvError.text=dataError
        })
        sesViewModel.getMad().observe(viewLifecycleOwner,{dataMad->
            binding.tvMad.text=dataMad
        })
        sesViewModel.getMse().observe(viewLifecycleOwner,{dataMse->
            binding.tvMse.text=dataMse
        })
        sesViewModel.getMape().observe(viewLifecycleOwner,{dataMape->
            binding.tvMape.text=dataMape
        })
        sesViewModel.getTs().observe(viewLifecycleOwner,{dataTs->
            binding.tvTs.text=dataTs
        })
    }


}