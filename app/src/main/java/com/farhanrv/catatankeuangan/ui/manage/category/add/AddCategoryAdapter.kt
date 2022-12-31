package com.farhanrv.catatankeuangan.ui.manage.category.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.core.domain.model.Category
import com.farhanrv.catatankeuangan.databinding.ItemLogoKategoriBinding

class AddCategoryAdapter(private val callback: AddCategoryCallback) : RecyclerView.Adapter<AddCategoryAdapter.NoteViewHolder>() {
    private val categoryList = ArrayList<Category>()

    fun setCategoryList(categoryList: List<Category>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemLogoKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class NoteViewHolder(private val binding: ItemLogoKategoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Category) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.logo)
                    .into(imgKategoriIcon)
                root.setOnClickListener {
                    callback.onIconClicked(data)
                }
            }
        }
    }
}