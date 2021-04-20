package com.yusril.kpperamalan.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.activity.alpha.AlphaActivity
import com.yusril.kpperamalan.activity.dataminyak.DataMinyakActivity
import com.yusril.kpperamalan.activity.des.DesActivity
import com.yusril.kpperamalan.activity.ses.SesActivity
import com.yusril.kpperamalan.adapter.ListMenuAdapter

import com.yusril.kpperamalan.databinding.ActivityMainBinding
import com.yusril.kpperamalan.model.Menu

class MainActivity : AppCompatActivity() {

    private lateinit var rvMenu: RecyclerView
    private lateinit var listMenuAdapter:ListMenuAdapter
    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<Menu> = arrayListOf()
    private lateinit var dataTitle: Array<String>
    private lateinit var dataColor: TypedArray
    private lateinit var dataIc: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvMenu=binding.rvMainMenu
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = LinearLayoutManager(this)
        listMenuAdapter = ListMenuAdapter(list)
        list.addAll(getListMenu())
        rvMenu.adapter = listMenuAdapter
        listMenuAdapter.setOnItemClickCallback(object :ListMenuAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Menu) {
                val intent:Intent
                when(data.title){
                    getString(R.string.Nilai_Parameter_Alpha)->{
                        Toast.makeText(this@MainActivity, getString(R.string.Nilai_Parameter_Alpha), Toast.LENGTH_SHORT).show()
                        intent=Intent(this@MainActivity, AlphaActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.Data_Harga_Minyak_Bumi)->{
                        Toast.makeText(this@MainActivity, getString(R.string.Data_Harga_Minyak_Bumi), Toast.LENGTH_SHORT).show()
                        intent=Intent(this@MainActivity, DataMinyakActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.Single_Exponential_Smoothing)->{
                        Toast.makeText(this@MainActivity, getString(R.string.Single_Exponential_Smoothing), Toast.LENGTH_SHORT).show()
                        intent=Intent(this@MainActivity, SesActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.Double_Exponential_Smoothing)->{
                        Toast.makeText(this@MainActivity, getString(R.string.Double_Exponential_Smoothing), Toast.LENGTH_SHORT).show()
                        intent=Intent(this@MainActivity, DesActivity::class.java)
                        startActivity(intent)
                    }

                }

            }
        })
    }



    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> true
        }
    }


    private fun getListMenu(): ArrayList<Menu> {
        val listMenu= ArrayList<Menu>()
        dataTitle = resources.getStringArray(R.array.data_title)
        dataColor = resources.obtainTypedArray(R.array.data_color)
        dataIc = resources.obtainTypedArray(R.array.data_ic)
        for(position in dataTitle.indices){
            val menu=Menu(
                    dataTitle[position],
                    dataColor.getResourceId(position, -1),
                    dataIc.getResourceId(position, -1)
            )
            listMenu.add(menu)
        }
        return listMenu
    }
}