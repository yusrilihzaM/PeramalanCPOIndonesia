package com.yusril.kpperamalan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.databinding.ItemDataMinyakBinding
import com.yusril.kpperamalan.databinding.ItemHasilBinding
import com.yusril.kpperamalan.model.DataMinyak
import com.yusril.kpperamalan.model.HasilSes

class ListHasilSesAdapter(private val listHasil: ArrayList<HasilSes>) : RecyclerView.Adapter<ListHasilSesAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemHasilBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hasilSes: HasilSes){
            binding.tvNo.text=hasilSes.id_data_minyak
            binding.tvTahun.text=hasilSes.tahun
            binding.tvBulan.text=hasilSes.bulan
            binding.tvAt.text=hasilSes.at
            if (hasilSes.ft=="" || hasilSes.ft=="null") {
                binding.tvFt.text="-"
            }
            binding.tvFt.text=hasilSes.ft
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemHasilBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHasil[position])
    }

    override fun getItemCount(): Int {
        return listHasil.size
    }
}