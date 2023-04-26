package com.example.handsup

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Word (
    var word: String,
    var isGuessed:Boolean
):Parcelable