package com.farhanrv.catatankeuangan.core.domain.usecase

import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface RepositoryUseCase {
    fun getAllNote(): Flow<List<Note>>

    fun insertNote(note: NoteEntity)

    fun updateNote(note: NoteEntity)

    fun getCategory(): Flow<List<CategoryEntity>>

    fun insertCategory(category: List<CategoryEntity>)

    fun updateCategory(category: CategoryEntity)
}