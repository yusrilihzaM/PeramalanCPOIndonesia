package com.yusril.kpperamalan.activity.dataminyak

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.ActivityAddDataBinding
import com.yusril.kpperamalan.databinding.ActivityDataDetailBinding
import com.yusril.kpperamalan.viewmodel.MainViewModel

class AddDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=getString(R.string.tambah_data)
        mainViewModel= ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        binding.btnSubmit.setOnClickListener {

            saveData()
            DataMinyakActivity.MyClass.activity?.finish()
            startActivity(Intent(
                this@AddDataActivity,
                DataMinyakActivity::class.java))
            Toast.makeText(
                this@AddDataActivity,
                getString(R.string.data_berhasil_disimpan),
                Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun saveData(){
        val tahun:Int=binding.edtTahun.text.toString().toInt()
        val bulan:Int=binding.edtBulan.text.toString().toInt()
        val harga:Double=binding.edtHarga.text.toString().toDouble()
        mainViewModel.postDataMinyak(tahun,bulan,harga)
    }
}