package com.farhanrv.catatankeuangan.core.utils

import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.model.Note

object NoteMapper {
    fun mapEntitiesToDomain(input: List<NoteEntity>): List<Note> {
        val noteList = ArrayList<Note>()

        input.map {
            val note = Note(
                id = it.id,
                title = it.title,
                jenis = it.jenis,
                kategori = it.kategori,
                kategoriLogo = it.kategoriLogo,
                nominal = it.nominal,
                date = it.date
            )
            noteList.add(note)
        }
        return noteList
    }
}