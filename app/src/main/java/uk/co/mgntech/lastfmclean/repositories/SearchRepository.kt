package uk.co.mgntech.lastfmclean.repositories

import androidx.lifecycle.LiveData
import uk.co.mgntech.lastfmclean.models.Album
import uk.co.mgntech.lastfmclean.models.Artist
import uk.co.mgntech.lastfmclean.models.Song
import uk.co.mgntech.lastfmclean.requests.SearchApiClient

class SearchRepository {

    private val _apiClient = SearchApiClient.instance

    fun artistsLoading(): LiveData<Boolean> = _apiClient.artistsLoading
    fun albumsLoading(): LiveData<Boolean> = _apiClient.albumsLoading
    fun songsLoading(): LiveData<Boolean> = _apiClient.songsLoading

    fun albums(): LiveData<List<Album>> = _apiClient.albums
    fun songs(): LiveData<List<Song>> = _apiClient.songs
    fun artists(): LiveData<List<Artist>> = _apiClient.artists

    fun searchArtistsApi(artist: String, pageNumber: Int) {
        _apiClient.searchArtistsApi(artist, pageNumber)
    }

    fun searchAlbumsApi(album: String, pageNumber: Int) {
        _apiClient.searchAlbumsApi(album, pageNumber)
    }

    fun searchSongsApi(song: String, pageNumber: Int) {
        _apiClient.searchSongsApi(song, pageNumber)
    }

    companion object {
        val instance = SearchRepository()
    }
}
