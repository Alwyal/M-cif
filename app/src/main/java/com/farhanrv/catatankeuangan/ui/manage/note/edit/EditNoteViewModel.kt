package com.farhanrv.catatankeuangan.ui.manage.note.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class EditNoteViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    val category = repository.getCategory().asLiveData()
    fun updateNote(note: NoteEntity) = repository.updateNote(note)
//    fun deleteSeason() = repository.deleteSeason()
}