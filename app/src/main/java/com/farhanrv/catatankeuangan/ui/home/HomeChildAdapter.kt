package com.farhanrv.catatankeuangan.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.databinding.ItemHomeSecondBinding
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.utils.NoteDiffUtil
import com.farhanrv.catatankeuangan.core.utils.RupiahHelper
import com.farhanrv.catatankeuangan.ui.manage.note.edit.EditNoteActivity

class HomeChildAdapter : RecyclerView.Adapter<HomeChildAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffUtil(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ItemHomeSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    inner class NoteViewHolder(private val binding: ItemHomeSecondBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listDistinct: Note) {
            with(binding) {
                tvTitle.text = listDistinct.title
                tvCategory.text = listDistinct.kategori
                tvNominal.text = RupiahHelper.rupiahFormatter(listDistinct.nominal.toInt())
                Glide.with(itemView.context)
                    .load(listDistinct.kategoriLogo)
                    .into(imgIcon)
                root.setOnClickListener {
                    val intent = Intent(itemView.context, EditNoteActivity::class.java)
                    intent.putExtra(EditNoteActivity.EXTRA_DATA, listDistinct)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}