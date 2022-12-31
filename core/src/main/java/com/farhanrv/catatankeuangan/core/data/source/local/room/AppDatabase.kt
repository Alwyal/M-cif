package com.farhanrv.catatankeuangan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity

@Database(entities = [NoteEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}