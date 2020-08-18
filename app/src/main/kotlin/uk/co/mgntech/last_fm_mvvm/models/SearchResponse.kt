package uk.co.mgntech.last_fm_mvvm.models

import com.google.gson.annotations.SerializedName

data class SearchResultsResponse(
    @SerializedName("results")
    private val results: SearchResults
) {
    fun results(): List<Search>? {
        return when {
            results.albumMatches != null -> return results.albumMatches.results
            results.artistMatches != null -> return results.artistMatches.results
            results.songMatches != null -> return results.songMatches.results
            else -> null
        }
    }
}

data class SearchResults(
    @SerializedName("artistmatches")
    val artistMatches: ArtistMatches?,
    @SerializedName("albummatches")
    val albumMatches: AlbumMatches?,
    @SerializedName("trackmatches")
    val songMatches: SongMatches?
)

data class ArtistMatches(
    @SerializedName("artist")
    val results: List<Search>
)

data class AlbumMatches(
    @SerializedName("album")
    val results: List<Search>
)

data class SongMatches(
    @SerializedName("track")
    val results: List<Search>
)
