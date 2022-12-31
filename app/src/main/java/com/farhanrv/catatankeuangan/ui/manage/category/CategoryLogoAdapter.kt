package com.farhanrv.catatankeuangan.ui.manage.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.core.domain.model.Category
import com.farhanrv.catatankeuangan.databinding.ItemLogoKategoriBinding

class CategoryLogoAdapter : RecyclerView.Adapter<CategoryLogoAdapter.ListViewHolder>() {

    private var listData = ArrayList<Category>()
    var onItemClick: ((Category) -> Unit)? = null

    fun setData(newListData: List<Category>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(ItemLogoKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemLogoKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Category) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.logo)
                    .into(imgKategoriIcon)
            }
        }
        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listData[adapterPosition]) }
        }
    }
}