package com.fiknaufalh.coffeenutrition.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val name: String,
    val description: String,
    val image: String,
) : Parcelable
