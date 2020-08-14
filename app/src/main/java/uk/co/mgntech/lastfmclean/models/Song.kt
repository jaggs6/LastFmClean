package uk.co.mgntech.lastfmclean.models

import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("listeners")
    val listeners: String
)