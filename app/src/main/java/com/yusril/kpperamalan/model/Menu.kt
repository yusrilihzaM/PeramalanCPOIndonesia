package com.yusril.kpperamalan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
        var title:String,
        var color:Int,
        var ic: Int
):Parcelable
