package com.yusril.kpperamalan.activity.ses

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentSesEvaluasiBinding
import com.yusril.kpperamalan.databinding.FragmentSesRamalBinding
import com.yusril.kpperamalan.model.RamalSes
import com.yusril.kpperamalan.viewmodel.SesViewModel
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class SesRamalFragment : Fragment() {
    companion object {
        private const val URL_HASIL_SES = BuildConfig.URL_HASIL_SES
        private const val URL_ERROR_SES = BuildConfig.URL_ERROR_SES
        private const val URL_MAD_SES = BuildConfig.URL_MAD_SES
        private const val URL_MSE_SES = BuildConfig.URL_MSE_SES
        private const val URL_TS_SES = BuildConfig.URL_TS_SES
        private const val URL_MAPE_SES = BuildConfig.URL_MAPE_SES
        private const val URL_RAMAL_SES = BuildConfig.URL_RAMAL_SES

    }
    private lateinit var sesViewModel: SesViewModel
    private lateinit var binding: FragmentSesRamalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ses_ramal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSesRamalBinding.bind(view)
        sesViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SesViewModel::class.java)
        sesViewModel.setRamal()
        sesViewModel.getRamal().observe(viewLifecycleOwner,{dataRamal->
            binding.progressBar.visibility=View.GONE
            Log.d("dataRamal", dataRamal.toString())
            binding.run {
                tvNo.text="1"
                tvTahun.text=dataRamal[0].tahun
                tvBulan.text=dataRamal[0].bulan
                val dtFt:Double=dataRamal[0].ft.toDouble()
                val ft:Double = String.format("%.3f", dataRamal[0].ft.toDouble()).toDouble()
                tvFt.text=ft.toString()
            }
        })

    }
    fun setRamal(){
        val listItems = ArrayList<RamalSes>()
        val client= AsyncHttpClient()
        client.get(URL_RAMAL_SES,object :AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    Log.d("tagRamal", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val dataArray = jsonObject.getJSONObject("data_ramal_ses")
                    Log.d("dataArray", dataArray.toString())
                    val dataItem = dataArray.getString("tahun")
                    Log.d("dataItem", dataItem.toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray, error: Throwable?) {
                Log.d("gagal", "gagal")
            }
        })
    }
}