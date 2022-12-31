package com.farhanrv.catatankeuangan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteDaily(
    val date: Long,
    val sum: Int
) : Parcelable
