package uk.co.mgntech.last_fm_mvvm.repositories

import androidx.lifecycle.LiveData
import uk.co.mgntech.last_fm_mvvm.models.Search
import uk.co.mgntech.last_fm_mvvm.models.SearchType
import uk.co.mgntech.last_fm_mvvm.requests.SearchApiClient

class SearchRepository {

    private val _apiClient = SearchApiClient.instance

    fun artistsLoading(): LiveData<Boolean> = _apiClient.artistsLoading
    fun albumsLoading(): LiveData<Boolean> = _apiClient.albumsLoading
    fun songsLoading(): LiveData<Boolean> = _apiClient.songsLoading

    fun albums(): LiveData<List<Search>> = _apiClient.albums
    fun songs(): LiveData<List<Search>> = _apiClient.songs
    fun artists(): LiveData<List<Search>> = _apiClient.artists

    fun searchApi(query: String, type: SearchType, pageNumber: Int) {
        _apiClient.searchApi(query, type, pageNumber)
    }

    companion object {
        val instance = SearchRepository()
    }
}
