package com.yusril.kpperamalan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.databinding.ItemHasilBinding
import com.yusril.kpperamalan.databinding.ItemHasilDesBinding
import com.yusril.kpperamalan.model.HasilDes
import com.yusril.kpperamalan.model.HasilSes

class ListHasilDesAdapter(private val listHasil: ArrayList<HasilDes>) : RecyclerView.Adapter<ListHasilDesAdapter.ListViewHolder>()  {
    inner class ListViewHolder(private val binding: ItemHasilDesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hasilDes: HasilDes){
           binding.run {
               tvNo.text=hasilDes.id_data_minyak
               tvAt.text=hasilDes.harga_minyak
               tvY.text=hasilDes.y_aksen_des
               tvYy.text=hasilDes.y_dbl_aksen_des
               tvA.text=hasilDes.a_des
               tvB.text=hasilDes.b_des
               tvFt.text=hasilDes.hasil_forecast_des
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemHasilDesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHasil[position])
    }

    override fun getItemCount(): Int {
        return listHasil.size
    }
}