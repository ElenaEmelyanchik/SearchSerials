package com.example.searchserials

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Serial(val name: String?, val genres: List<String>?, val image: String?): Parcelable