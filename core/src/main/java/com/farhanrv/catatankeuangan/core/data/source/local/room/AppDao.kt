package com.farhanrv.catatankeuangan.core.data.source.local.room

import androidx.room.*
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM note_entity ORDER BY date DESC")
    fun getAllNote(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM category_entity")
    fun getCategory(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(note: List<CategoryEntity>)

    @Update
    fun updateCategory(category: CategoryEntity)

    @Update
    fun updateNote(note: NoteEntity)
}