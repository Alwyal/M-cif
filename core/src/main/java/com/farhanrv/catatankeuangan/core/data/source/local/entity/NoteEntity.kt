package com.farhanrv.catatankeuangan.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_entity")
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "jenis")
    var jenis: String,

    @ColumnInfo(name = "kategori")
    var kategori: String,

    @ColumnInfo(name = "kategori_logo")
    var kategoriLogo: Int,

    @ColumnInfo(name = "nominal")
    var nominal: String,

    @ColumnInfo(name = "date")
    var date: Long
) : Parcelable
