package com.yusril.kpperamalan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusril.kpperamalan.databinding.ItemDataMinyakBinding
import com.yusril.kpperamalan.model.DataMinyak

class ListDataMInyakAdapter(private val listData: ArrayList<DataMinyak>) : RecyclerView.Adapter<ListDataMInyakAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    inner class ListViewHolder(private val binding: ItemDataMinyakBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataMinyak: DataMinyak){
            binding.tvNo.text=dataMinyak.id_data_minyak
            binding.tvTahun.text=dataMinyak.tahun
            binding.tvBulan.text=dataMinyak.bulan
            binding.tvHarga.text=dataMinyak.harga_minyak
            itemView.setOnClickListener{
                onItemClickCallback?.onItemClicked(dataMinyak)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemDataMinyakBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(dataMinyak: DataMinyak)
    }
}