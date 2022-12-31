package com.farhanrv.catatankeuangan.core.data

import com.farhanrv.catatankeuangan.core.data.source.local.AppExecutors
import com.farhanrv.catatankeuangan.core.data.source.local.LocalDataSource
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.domain.repository.IRepository
import com.farhanrv.catatankeuangan.core.utils.NoteMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRepository {
    override fun getAllNote(): Flow<List<Note>> =
        localDataSource.getAllNote().map {
            NoteMapper.mapEntitiesToDomain(it)
        }

    override fun insertNote(note: NoteEntity) = appExecutors.diskIO().execute{
        localDataSource.insertNote(note)
    }

    override fun updateNote(note: NoteEntity) = appExecutors.diskIO().execute{
        localDataSource.updateNote(note)
    }

    // delete feature
//    fun deleteSeason() = localDataSource.delete()

    override fun getCategory(): Flow<List<CategoryEntity>> =
        localDataSource.getCategory().map {
            it
        }

    override fun insertCategory(category: List<CategoryEntity>) = appExecutors.diskIO().execute{
        localDataSource.insertCategory(category)
    }

    override fun updateCategory(category: CategoryEntity) = appExecutors.diskIO().execute{
        localDataSource.updateCategory(category)
    }

}