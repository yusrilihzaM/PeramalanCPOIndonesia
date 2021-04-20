package com.yusril.kpperamalan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.kpperamalan.databinding.ItemRamalBinding
import com.yusril.kpperamalan.model.RamalDes

class ListRamalDesAdapter(private val listHasil: ArrayList<RamalDes>) : RecyclerView.Adapter<ListRamalDesAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRamalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ramalDes: RamalDes){
            binding.run {
                tvTahun.text=ramalDes.tahun
                tvBulan.text=ramalDes.bulan
                tvNo.text=ramalDes.id
                tvFt.text=ramalDes.ft

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemRamalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHasil[position])
    }

    override fun getItemCount(): Int {
        return listHasil.size
    }
}