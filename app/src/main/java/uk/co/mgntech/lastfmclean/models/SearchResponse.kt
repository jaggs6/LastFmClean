package uk.co.mgntech.lastfmclean.models

import com.google.gson.annotations.SerializedName

data class SearchResultsResponse(
    @SerializedName("results")
    private val results: SearchResults
) {
    fun artists(): List<Artist>? = results.artistMatches?.artists

    fun albums(): List<Album>? = results.albumMatches?.albums

    fun songs(): List<Song>? = results.songMatches?.songs
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
    val artists: List<Artist>
)

data class AlbumMatches(
    @SerializedName("album")
    val albums: List<Album>
)

data class SongMatches(
    @SerializedName("track")
    val songs: List<Song>
)
