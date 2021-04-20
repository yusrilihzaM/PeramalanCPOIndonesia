package com.yusril.kpperamalan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMinyak(
    var id_data_minyak:String,
    var tahun:String,
    var bulan:String,
    var harga_minyak:String,
    var t:String,
):Parcelable
