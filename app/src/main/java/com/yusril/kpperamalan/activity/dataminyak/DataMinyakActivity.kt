package com.yusril.kpperamalan.activity.dataminyak

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient.log
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.activity.SettingsActivity
import com.yusril.kpperamalan.adapter.ListDataMInyakAdapter
import com.yusril.kpperamalan.databinding.ActivityAlphaBinding
import com.yusril.kpperamalan.databinding.ActivityDataMinyakBinding
import com.yusril.kpperamalan.databinding.ActivityMainBinding
import com.yusril.kpperamalan.model.DataMinyak
import com.yusril.kpperamalan.viewmodel.MainViewModel

class DataMinyakActivity : AppCompatActivity() {
    private lateinit var listDataMInyakAdapter: ListDataMInyakAdapter
    private lateinit var binding: ActivityDataMinyakBinding
    private lateinit var mainViewModel: MainViewModel
    class MyClass{
        companion object{
            var activity: Activity? = null
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_minyak)
        binding = ActivityDataMinyakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title=getString(R.string.data_harga)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        DataMinyakActivity.MyClass.activity = this@DataMinyakActivity
        mainViewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager= LinearLayoutManager(this)
        mainViewModel.setDataMinyak()
        showLoading(true)
        mainViewModel.getDataMinyak().observe(this,{dataItems->
            listDataMInyakAdapter= ListDataMInyakAdapter(dataItems)
            showLoading(false)
            binding.rvList.adapter=listDataMInyakAdapter
            listDataMInyakAdapter.setOnItemClickCallback(object :ListDataMInyakAdapter.OnItemClickCallback{
                override fun onItemClicked(dataMinyak: DataMinyak) {
                    Toast.makeText(this@DataMinyakActivity, dataMinyak.t, Toast.LENGTH_SHORT).show()
                    val intent=Intent(this@DataMinyakActivity, DataDetailActivity::class.java)
                    intent.putExtra(DataDetailActivity.EXTRA_DATA,dataMinyak)
                    startActivity(intent)

                }
            })
        })
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.data_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("item", "item i "+item.getItemId())
        return when (item.itemId) {
            R.id.add -> {
                startActivity(Intent(this, AddDataActivity::class.java))
                true
            }
            16908332->{
                this.finish()
                true
            }
            else -> true
        }
    }

}