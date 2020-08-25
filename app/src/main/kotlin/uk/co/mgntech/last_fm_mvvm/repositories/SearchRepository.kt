package uk.co.mgntech.last_fm_mvvm.repositories

import io.reactivex.disposables.Disposable
import uk.co.mgntech.last_fm_mvvm.models.SearchType
import uk.co.mgntech.last_fm_mvvm.requests.SearchApiClient

class SearchRepository(private val api_client: SearchApiClient) {

    companion object {
        val instance = SearchRepository(SearchApiClient.instance)
    }

    fun artistsLoading() = api_client.artistsLoading
    fun albumsLoading() = api_client.albumsLoading
    fun songsLoading() = api_client.songsLoading

    fun albums() = api_client.albums
    fun songs() = api_client.songs
    fun artists() = api_client.artists

    fun searchApi(query: String, type: SearchType): Disposable {
        return api_client.searchApi(query, type)
    }
}
