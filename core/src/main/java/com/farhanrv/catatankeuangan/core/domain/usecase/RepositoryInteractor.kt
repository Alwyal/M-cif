package com.farhanrv.catatankeuangan.core.domain.usecase

import com.farhanrv.catatankeuangan.core.data.AppRepository
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class RepositoryInteractor(private val repository: IRepository) : RepositoryUseCase {
    override fun getAllNote(): Flow<List<Note>> = repository.getAllNote()

    override fun insertNote(note: NoteEntity) = repository.insertNote(note)

    override fun updateNote(note: NoteEntity) = repository.updateNote(note)

    override fun getCategory(): Flow<List<CategoryEntity>> = repository.getCategory()

    override fun insertCategory(category: List<CategoryEntity>) = repository.insertCategory(category)

    override fun updateCategory(category: CategoryEntity) = repository.updateCategory(category)
}