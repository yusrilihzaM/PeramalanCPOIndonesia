package com.yusril.kpperamalan.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.activity.ses.SesEvaluasiFragment
import com.yusril.kpperamalan.model.DataMinyak
import com.yusril.kpperamalan.model.HasilDes
import com.yusril.kpperamalan.model.HasilSes
import com.yusril.kpperamalan.model.RamalSes
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class SesViewModel: ViewModel() {
    companion object {
        private const val URL_HASIL_SES = BuildConfig.URL_HASIL_SES
        private const val URL_ERROR_SES = BuildConfig.URL_ERROR_SES
        private const val URL_MAD_SES = BuildConfig.URL_MAD_SES
        private const val URL_MSE_SES = BuildConfig.URL_MSE_SES
        private const val URL_TS_SES = BuildConfig.URL_TS_SES
        private const val URL_MAPE_SES = BuildConfig.URL_MAPE_SES
        private const val URL_RAMAL_SES = BuildConfig.URL_RAMAL_SES

    }
    val error = MutableLiveData<String>()
    val mad = MutableLiveData<String>()
    val mse = MutableLiveData<String>()
    val mape = MutableLiveData<String>()
    val ts = MutableLiveData<String>()
    val listHasil = MutableLiveData<ArrayList<HasilSes>>()
    val listRamal = MutableLiveData<ArrayList<RamalSes>>()
    fun setRamal(){
        val listItems = ArrayList<RamalSes>()
        val client= AsyncHttpClient()
        client.get(URL_RAMAL_SES,object :AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val dataObject = jsonObject.getJSONObject("data_ramal_ses")
                    val tahun = dataObject.getString("tahun")
                    val bulan = dataObject.getString("bulan")
                    val ft = dataObject.getString("hasil_forecast")
                    val ramalSes = RamalSes(tahun, bulan, ft)
                    listItems.add(ramalSes)
                    listRamal.postValue(listItems)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"

                }
                Log.d("onFailure", errorMessage)
            }
        })
    }
    fun setHasil(){
        val listItems = ArrayList<HasilSes>()
        val client= AsyncHttpClient()
        client.get(URL_HASIL_SES,object :AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val dataArray = jsonObject.getJSONArray("data_ses")
                    Log.d("dataArray", dataArray.toString())
                    for (i in 0 until dataArray.length()) {
                        val dataItem = dataArray.getJSONObject(i)
                        val id_data_minyak = dataItem.getString("id_data_minyak")
                        val tahun = dataItem.getString("tahun")
                        val bulan = dataItem.getString("bulan")
                        val at = dataItem.getString("harga_minyak")
                        val ft = dataItem.getString("y_aksen_ses")
                        val hasilSes = HasilSes(id_data_minyak, tahun, bulan, at, ft)
                        listItems.add(hasilSes)
                    }
                    listHasil.postValue(listItems)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"

                }
                Log.d("onFailure", errorMessage)
            }
        })
    }
    fun setMad(){
        val client= AsyncHttpClient()
        client.get(URL_MAD_SES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val nilaiMAD = jsonObject.getString("mad_ses")
                    Log.d("nilai_mad", nilaiMAD.toString())
                    mad.postValue(nilaiMAD)
                } catch (e: Exception) {

                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }
    fun setError(){
        val client= AsyncHttpClient()
        client.get(URL_ERROR_SES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val nilaiError = jsonObject.getString("error_ses")
                    Log.d("nilai_alpha", nilaiError.toString())
                    error.postValue(nilaiError)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }
    fun setMSE(){
        val client= AsyncHttpClient()
        client.get(URL_MSE_SES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val nilaiMSE = jsonObject.getString("mse_ses")
                    Log.d("nilai_mse", nilaiMSE.toString())
                  mse.postValue(nilaiMSE)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }
    fun setMAPE(){
        val client= AsyncHttpClient()
        client.get(URL_MAPE_SES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag_ses", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject_ses", jsonObject.toString())
                    val nilaiMAPE = jsonObject.getString("mape_ses")
                    Log.d("nilai_mape_ses", nilaiMAPE.toString())
                    mape.postValue(nilaiMAPE)
                } catch (e: Exception) {

                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }
    fun setTS(){
        val client= AsyncHttpClient()
        client.get(URL_TS_SES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val nilaiTS = jsonObject.getString("ts_ses")
                    Log.d("nilai_ts", nilaiTS.toString())
                    ts.postValue(nilaiTS)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getRamal(): LiveData<ArrayList<RamalSes>> {
        return listRamal
    }
    fun getHasil(): LiveData<ArrayList<HasilSes>> {
        return listHasil
    }
    fun getError(): LiveData<String> {
        return error
    }
    fun getMad(): LiveData<String> {
        return mad
    }
    fun getMse(): LiveData<String> {
        return mse
    }fun getMape(): LiveData<String> {
        return mape
    }
    fun getTs(): LiveData<String> {
        return ts
    }

}