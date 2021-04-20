package com.yusril.kpperamalan.activity.dataminyak

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.ActivityDataDetailBinding
import com.yusril.kpperamalan.model.DataMinyak
import com.yusril.kpperamalan.viewmodel.MainViewModel
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject


class DataDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
        private const val URL_DATAMINYAK = BuildConfig.URL_DATAMINYAK
    }


    private lateinit var dt_id_data_minyak:String
    private lateinit var dt_tahun:String
    private lateinit var dt_bulan:String
    private lateinit var dt_harga_minyak:String
    private lateinit var dt_t:String

    private lateinit var binding: ActivityDataDetailBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)
        binding = ActivityDataDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        val dataMinyak=intent.getParcelableExtra<DataMinyak>(EXTRA_DATA) as DataMinyak
        dt_id_data_minyak = dataMinyak.id_data_minyak
        dt_tahun = dataMinyak.tahun
        dt_bulan = dataMinyak.bulan
        dt_harga_minyak = dataMinyak.harga_minyak
        dt_t = dataMinyak.t
        supportActionBar?.title=getString(R.string.detail_data)+dt_id_data_minyak
        binding.edtTahun.setText(dt_tahun)
        binding.edtBulan.setText(dt_bulan)
        binding.edtHarga.setText(dt_harga_minyak)
        binding.btnSubmit.setOnClickListener {

            val tahun:Int=binding.edtTahun.text.toString().toInt()
            val bulan:Int=binding.edtBulan.text.toString().toInt()
            val harga:Double=binding.edtHarga.text.toString().toDouble()
            DataMinyakActivity.MyClass.activity?.finish()
            mainViewModel.putDataMinyak(dt_id_data_minyak.toInt(), tahun, bulan, harga)

            startActivity(Intent(this@DataDetailActivity, DataMinyakActivity::class.java))
            Toast.makeText(this@DataDetailActivity, getString(R.string.update_success), Toast.LENGTH_SHORT).show()
            finish()

        }
    }
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.detail_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.del -> {
                delData(ALERT_DIALOG_DELETE)
                true
            }
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }
    private fun delData(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if (isDialogClose) {
            dialogTitle = getString(R.string.batal)
            dialogMessage = getString(R.string.yakin_batal_menghapus)
        } else {
            dialogMessage = getString(R.string.yakin_menghapus)
            dialogTitle = getString(R.string.hapus_data_minyak)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("Ya") { _, _ ->
                    if (isDialogClose) {
                        finish()
                    } else {
                        mainViewModel.delDataMinyak(dt_id_data_minyak.toInt())

                        DataMinyakActivity.MyClass.activity?.finish()
                        startActivity(Intent(this@DataDetailActivity, DataMinyakActivity::class.java))
                        Toast.makeText(this@DataDetailActivity, getString(R.string.hapus_berhasil), Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .setNegativeButton("Tidak") { dialog, _ -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}