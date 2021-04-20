package com.yusril.kpperamalan.adapter

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusril.kpperamalan.databinding.ItemMainMenuBinding
import com.yusril.kpperamalan.model.Menu

class ListMenuAdapter(private val listMenu: ArrayList<Menu>): RecyclerView.Adapter<ListMenuAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemMainMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu){
            with(binding){
                Glide.with(itemView.context)
                        .load(menu.ic)
                        .into(icMenu)
                tvTitle.text = menu.title
                card.background=itemView.context.getDrawable(menu.color)
                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(menu)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding=ItemMainMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }
}