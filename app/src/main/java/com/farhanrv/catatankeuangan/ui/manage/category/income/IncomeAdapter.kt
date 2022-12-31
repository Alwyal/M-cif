package com.farhanrv.catatankeuangan.ui.manage.category.income

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.databinding.ItemListCategoryBinding
import com.farhanrv.catatankeuangan.ui.manage.category.add.AddCategoryActivity

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.NoteViewHolder>() {
    private val categoryList = ArrayList<CategoryEntity>()

    fun setCategoryList(categoryList: List<CategoryEntity>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class NoteViewHolder(private val binding: ItemListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryEntity) {
            with(binding) {
                tvCategoryTitle.text = data.name
                Glide.with(itemView.context)
                    .load(data.logoId)
                    .into(imgLogo)
                root.setOnClickListener {
                    val intent = Intent(itemView.context, AddCategoryActivity::class.java)
                    intent.putExtra(AddCategoryActivity.EXTRA_DATA, data)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}