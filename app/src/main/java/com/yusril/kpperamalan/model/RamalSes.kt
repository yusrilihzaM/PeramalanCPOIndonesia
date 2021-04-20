package com.yusril.kpperamalan.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RamalSes(
        var tahun:String,
        var bulan:String,
        var ft:String
        ):Parcelable
