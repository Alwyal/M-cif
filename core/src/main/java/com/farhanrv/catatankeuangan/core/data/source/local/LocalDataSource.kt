package com.farhanrv.catatankeuangan.core.data.source.local

import android.util.Log
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val appDao: AppDao) {
    fun getAllNote(): Flow<List<NoteEntity>> = appDao.getAllNote()

    fun insertNote(user: NoteEntity) = appDao.insertNote(user)

    fun getCategory(): Flow<List<CategoryEntity>> = appDao.getCategory()

    fun insertCategory(category: List<CategoryEntity>) = appDao.insertCategory(category)

    fun updateCategory(category: CategoryEntity) = appDao.updateCategory(category)

    fun updateNote(note: NoteEntity) {
        appDao.updateNote(note)
        Log.e("LocalDataSource", note.toString())
    }
}