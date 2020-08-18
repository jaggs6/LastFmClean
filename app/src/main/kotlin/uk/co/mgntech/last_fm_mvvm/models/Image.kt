package uk.co.mgntech.last_fm_mvvm.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,
    @SerializedName("size")
    val size: String
)
