package com.yusril.kpperamalan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.model.DataMinyak
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject


class MainViewModel: ViewModel() {
    companion object {
        private const val URL_DATAMINYAK = BuildConfig.URL_DATAMINYAK
        private const val URL_DELETE_DATAMINYAK = BuildConfig.URL_DELETE_DATAMINYAK
    }
    val listDataMinyak = MutableLiveData<ArrayList<DataMinyak>>()

    fun setDataMinyak(){
        val listItems = ArrayList<DataMinyak>()
        val client= AsyncHttpClient()
        client.get(URL_DATAMINYAK, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    Log.d("tag", result)
                    val jsonObject = JSONObject(result)
                    Log.d("jsonObject", jsonObject.toString())
                    val dataArray = jsonObject.getJSONArray("data_minyak")
                    Log.d("dataArray", dataArray.toString())
                    for (i in 0 until dataArray.length()) {
                        val dataItem = dataArray.getJSONObject(i)
                        val id_data_minyak = dataItem.getString("id_data_minyak")
                        val tahun = dataItem.getString("tahun")
                        val bulan = dataItem.getString("bulan")
                        val harga_minyak = dataItem.getString("harga_minyak")
                        val t = dataItem.getString("t")
                        val dataMinyak = DataMinyak(id_data_minyak, tahun, bulan, harga_minyak, t)
                        listItems.add(dataMinyak)
                    }
                    listDataMinyak.postValue(listItems)

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
    fun putDataMinyak(id: Int, tahun: Int, bulan: Int, harga: Double){
        val client= AsyncHttpClient()
        val params = RequestParams()
        params.put("id_data_minyak", id)
        params.put("tahun", tahun)
        params.put("bulan", bulan)
        params.put("harga_minyak", harga)
        client.put(URL_DATAMINYAK, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                Log.d("onSuccess", "Data Berhasil di Update")
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
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
    fun postDataMinyak(tahun: Int, bulan: Int, harga: Double){
        val client= AsyncHttpClient()
        val params = RequestParams()

        params.put("tahun", tahun)
        params.put("bulan", bulan)
        params.put("harga_minyak", harga)
        client.post(URL_DATAMINYAK, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
            ) {
                Log.d("onSuccess", "Data Berhasil di Ditambahkan")
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
            ) {
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
    fun delDataMinyak(id: Int){
        val client= AsyncHttpClient()
        val params = RequestParams()
        params.put("id", id)
        client.get(URL_DELETE_DATAMINYAK, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                Log.d("onSuccess", "Data Berhasil di Dihapus")
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray, error: Throwable?) {
                Log.d("onFailure", "Data Gagal di Dihapus")
            }
        })
    }
    fun getDataMinyak(): LiveData<ArrayList<DataMinyak>> {
        return listDataMinyak
    }
}