package com.farhanrv.catatankeuangan.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_entity")
@Parcelize
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val jenis: String,
    val logoId: Int
) : Parcelable

