package uk.co.mgntech.last_fm_mvvm.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("#text")
    val url: String,
    @SerializedName("size")
    val size: String
) : Parcelable
