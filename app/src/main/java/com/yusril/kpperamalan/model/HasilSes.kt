package com.yusril.kpperamalan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class HasilSes(
        var id_data_minyak:String,
        var tahun:String,
        var bulan:String,
        var at:String,
        var ft:String
):Parcelable
