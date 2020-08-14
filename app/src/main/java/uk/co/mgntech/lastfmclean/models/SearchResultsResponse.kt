package uk.co.mgntech.lastfmclean.models

import com.google.gson.annotations.SerializedName

data class SearchResultsResponse(
    @SerializedName("results")
    private val results: SearchResults
) {
    fun getArtists(): List<Artist>? {
        return results.getArtists()
    }

    fun getAlbums(): List<Album>? {
        return results.getAlbums()
    }

    fun getSongs(): List<Song>? {
        return results.getSongs()
    }
}

data class SearchResults(
    @SerializedName("artistmatches")
    private val artistMatches: ArtistMatches?,
    @SerializedName("albummatches")
    private val albumMatches: AlbumMatches?,
    @SerializedName("trackmatches")
    private val trackMatches: TrackMatches?
) {
    fun getArtists(): List<Artist>? {
        return artistMatches?.getArtists()
    }

    fun getAlbums(): List<Album>? {
        return albumMatches?.getAlbums()
    }

    fun getSongs(): List<Song>? {
        return trackMatches?.getSongs()
    }
}

data class ArtistMatches(
    @SerializedName("artist")
    private val artists: List<Artist>
) {
    fun getArtists(): List<Artist> {
        return artists
    }
}

data class AlbumMatches(
    @SerializedName("album")
    private val albums: List<Album>
) {
    fun getAlbums(): List<Album> {
        return albums
    }
}

data class TrackMatches(
    @SerializedName("track")
    private val songs: List<Song>
) {
    fun getSongs(): List<Song> {
        return songs
    }
}