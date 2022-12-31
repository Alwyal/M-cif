package com.farhanrv.catatankeuangan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Int,
    var title: String,
    var jenis: String,
    var kategori: String,
    var kategoriLogo: Int,
    var nominal: String,
    var date: Long
) : Parcelable
