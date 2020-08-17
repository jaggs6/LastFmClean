package uk.co.mgntech.lastfmclean.models

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("image")
    private val images: List<Image>
) {
    fun imageSmall(): String? {
        return images.find { it.size == "small" }?.url
    }

    fun imageLarge(): String? {
        return images.find { it.size == "large" }?.url
    }
}
