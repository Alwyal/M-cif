package com.farhanrv.catatankeuangan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farhanrv.catatankeuangan.databinding.ItemHomeMainBinding
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.utils.DateConverters
import com.farhanrv.catatankeuangan.core.utils.RupiahHelper

class HomeParentAdapter : RecyclerView.Adapter<HomeParentAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()
    private val dateList = ArrayList<Long>()
    fun setListNotes(listNotes: List<Note>, dateList: List<Long>) {
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)

        this.dateList.clear()
        this.dateList.addAll(dateList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemHomeMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (position <= dateList.size-1) {
            val filterByDate: List<Note> = listNotes.filter { it.date == dateList[position] }
            holder.bind(dateList[position], filterByDate)
        }
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    inner class NoteViewHolder(private val binding: ItemHomeMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Long, filterByDate: List<Note>) {

            var sum = 0
            for (data in filterByDate) {
                sum += data.nominal.toInt()
            }
            val childAdapter = HomeChildAdapter()
            childAdapter.setListNotes(filterByDate)
            with(binding) {
                tvNumber.text = RupiahHelper.rupiahFormatter(sum)
                tvDate.text = DateConverters.convertLongToTime(date)
                recyclerViewSecond.layoutManager = LinearLayoutManager(itemView.context)
                recyclerViewSecond.adapter = childAdapter
            }
        }
    }
}