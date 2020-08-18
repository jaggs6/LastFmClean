package uk.co.mgntech.last_fm_mvvm.models

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("listeners")
    val listeners: String?,
    @SerializedName("image")
    private val images: List<Image>
) {
    fun imageLarge(): String? {
        return images.findLast { it.size == "large" }?.url
    }
}
