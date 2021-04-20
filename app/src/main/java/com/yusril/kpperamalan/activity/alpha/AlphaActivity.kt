package com.yusril.kpperamalan.activity.alpha

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.ActivityAlphaBinding
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class AlphaActivity : AppCompatActivity() {
    companion object {
        private const val URL_ALPHA = BuildConfig.URL_ALPHA
    }

    private lateinit var binding: ActivityAlphaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alpha)
        binding = ActivityAlphaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=getString(R.string.parameter_alpha)
        loadAlpha()
        binding.btnSubmit.setOnClickListener {
            val id =1
            val alpha: Double = binding.edtAlpha.text.toString().toDouble()
            val params = RequestParams()
            params.put("id",id)
            params.put("alpha",alpha)
            val client= AsyncHttpClient()
            client.put(URL_ALPHA,params,object :AsyncHttpResponseHandler(){
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?
                ) {
                    Toast.makeText(
                        this@AlphaActivity,
                        getString(R.string.update_success),
                        Toast.LENGTH_SHORT).show()
                    finish()
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    Toast.makeText(
                        this@AlphaActivity,
                        getString(R.string.update_fail),
                        Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    private fun loadAlpha(){
        val client= AsyncHttpClient()
        client.get(URL_ALPHA, object : AsyncHttpResponseHandler() {
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
                    val dataArray = jsonObject.getJSONArray("data_alpha")
                    Log.d("dataArray", dataArray.toString())
                    val dataObject = dataArray.getJSONObject(0)
                    Log.d("dataObject", dataObject.toString())
                    val nilaiAlpha = dataObject.getString("alpha")
                    Log.d("nilai_alpha", nilaiAlpha.toString())
                    binding.tvAlpha.text = nilaiAlpha
                } catch (e: Exception) {
                    Toast.makeText(this@AlphaActivity, "Catch", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this@AlphaActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}