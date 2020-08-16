package uk.co.mgntech.lastfmclean.models

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("listeners")
    val listeners: String
)
