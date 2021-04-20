package com.yusril.kpperamalan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alpha(
    var id_alpha:String,
    var alpha:String
): Parcelable
