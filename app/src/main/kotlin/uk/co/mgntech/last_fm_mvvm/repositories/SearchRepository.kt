package uk.co.mgntech.last_fm_mvvm.repositories

import io.reactivex.disposables.Disposable
import uk.co.mgntech.last_fm_mvvm.models.SearchType
import uk.co.mgntech.last_fm_mvvm.requests.SearchApiClient

class SearchRepository {

    companion object {
        val instance = SearchRepository()
    }

    private val _apiClient = SearchApiClient.instance

    fun artistsLoading() = _apiClient.artistsLoading
    fun albumsLoading() = _apiClient.albumsLoading
    fun songsLoading() = _apiClient.songsLoading

    fun albums() = _apiClient.albums
    fun songs() = _apiClient.songs
    fun artists() = _apiClient.artists

    fun searchApi(query: String, type: SearchType): Disposable {
        return _apiClient.searchApi(query, type)
    }
}
