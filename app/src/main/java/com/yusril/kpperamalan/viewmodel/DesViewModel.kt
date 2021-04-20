package com.yusril.kpperamalan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.model.HasilDes
import com.yusril.kpperamalan.model.HasilSes
import com.yusril.kpperamalan.model.RamalDes
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


class DesViewModel: ViewModel() {

    companion object {
        private const val URL_HASIL_DES = BuildConfig.URL_HASIL_DES
        private const val URL_ERROR_DES = BuildConfig.URL_ERROR_DES
        private const val URL_MAD_DES = BuildConfig.URL_MAD_DES
        private const val URL_MSE_DES = BuildConfig.URL_MSE_DES
        private const val URL_TS_DES = BuildConfig.URL_TS_DES
        private const val URL_MAPE_DES = BuildConfig.URL_MAPE_DES
        private const val URL_RAMAL_DES = BuildConfig.URL_RAMAL_DES
    }
    val error = MutableLiveData<String>()
    val mad = MutableLiveData<String>()
    val mse = MutableLiveData<String>()
    val mape = MutableLiveData<String>()
    val ts = MutableLiveData<String>()
    val listHasil = MutableLiveData<ArrayList<HasilDes>>()
    val listRamal = MutableLiveData<ArrayList<RamalDes>>()
    fun setProsesRamal(bulan:Int){
        val client= AsyncHttpClient()
        val params = RequestParams()

        params.put("bulan", bulan)
        client.post(URL_RAMAL_DES,params,object:AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                Log.d("onSuccess", statusCode.toString())
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setRamalList(){
        val listItems = ArrayList<RamalDes>()
        val client= AsyncHttpClient()
        client.get(URL_RAMAL_DES,object :AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("result", error.toString())
                    val jsonObject = JSONObject(result)
                    val dataArray = jsonObject.getJSONArray("data_ramal")
                    for (i in 0 until dataArray.length()) {
                        val dataItem = dataArray.getJSONObject(i)
                        val id=dataItem.getString("id_ramal_des")
                        val tahun=dataItem.getString("tahun_des")
                        val bulan=dataItem.getString("bulan_des")
                        val ft=dataItem.getString("harga_minyak_des")

                        val ramalDes=RamalDes(
                            id,
                            tahun,
                            bulan,
                            ft
                        )
                        listItems.add(ramalDes)
                    }
                    listRamal.postValue(listItems)

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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setHasil(){
        val listItems = ArrayList<HasilDes>()
        val client= AsyncHttpClient()
        client.get(URL_HASIL_DES,object :AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val dataArray = jsonObject.getJSONArray("data_des")
                    for (i in 0 until dataArray.length()) {
                        val dataItem = dataArray.getJSONObject(i)
                        val id_data_minyak=dataItem.getString("id_data_minyak")
                        val harga_minyak=dataItem.getString("harga_minyak")
                        val y_aksen_des=dataItem.getString("y_aksen_des")
                        val y_dbl_aksen_des=dataItem.getString("y_dbl_aksen_des")
                        val a_des=dataItem.getString("a_des")
                        val b_des=dataItem.getString("b_des")
                        var hasil_forecast_des=dataItem.getString("hasil_forecast_des")
                        val hasilDes = HasilDes(
                            id_data_minyak,
                            harga_minyak,
                            y_aksen_des,
                            y_dbl_aksen_des,
                            a_des,
                            b_des,
                            hasil_forecast_des
                        )
                        listItems.add(hasilDes)
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
        client.get(URL_MAD_DES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val nilaiMAD = jsonObject.getString("mad_des")
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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setError(){
        val client= AsyncHttpClient()
        client.get(URL_ERROR_DES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag_error", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val nilaiError = jsonObject.getString("error_des")
                    Log.d("nilai_error", nilaiError.toString())
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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setMSE(){
        val client= AsyncHttpClient()
        client.get(URL_MSE_DES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val nilaiMSE = jsonObject.getString("mse_des")
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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setMAPE(){
        val client= AsyncHttpClient()
        client.get(URL_MAPE_DES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val nilaiMAPE = jsonObject.getString("mape_des")
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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun setTS(){
        val client= AsyncHttpClient()
        client.get(URL_TS_DES,object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val jsonObject = JSONObject(result)
                    val nilaiTS = jsonObject.getString("ts_des")
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
                Log.d("onFailure", error.toString())
            }
        })
    }
    fun getRamal(): LiveData<ArrayList<RamalDes>> {
        return listRamal
    }
    fun getHasil(): LiveData<ArrayList<HasilDes>> {
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