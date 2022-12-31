package com.farhanrv.catatankeuangan.ui.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.core.domain.model.CategoryNominal
import com.farhanrv.catatankeuangan.databinding.ItemFinancialReportsBinding

class FinancialReportAdapter : RecyclerView.Adapter<FinancialReportAdapter.NoteViewHolder>() {
    private val listPengeluaran = ArrayList<CategoryNominal>()
    fun setListNotes(list: List<CategoryNominal>) {
        this.listPengeluaran.clear()
        this.listPengeluaran.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemFinancialReportsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val data = listPengeluaran[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int {
        return listPengeluaran.size
    }

    inner class NoteViewHolder(private val binding: ItemFinancialReportsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryNominal) {
            with(binding) {
                tvKategori.text = data.kategori
                tvNominal.text = data.nominal.toString()
                Glide.with(itemView.context)
                    .load(data.logo)
                    .into(imageView)
            }
        }
    }
}