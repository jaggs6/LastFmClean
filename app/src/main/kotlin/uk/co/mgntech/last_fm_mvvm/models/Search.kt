package uk.co.mgntech.last_fm_mvvm.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artist: String?,
    @SerializedName("listeners")
    val listeners: String?,
    @SerializedName("image")
    private val images: List<Image>?
) : Parcelable {
    fun imageLarge(): String? {
        return images?.findLast { it.size == "large" }?.url
    }

    fun imageXLarge(): String? {
        return images?.findLast { it.size == "extralarge" }?.url
    }
}
