package com.farhanrv.catatankeuangan.ui.manage.note.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class AddNoteViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    fun insertNote(note: NoteEntity) = repository.insertNote(note)
    val category = repository.getCategory().asLiveData()
//    fun deleteSeason() = repository.deleteSeason()
}