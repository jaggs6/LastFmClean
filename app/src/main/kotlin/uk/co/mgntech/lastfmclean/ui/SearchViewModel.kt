package uk.co.mgntech.lastfmclean.ui

import androidx.lifecycle.ViewModel
import uk.co.mgntech.lastfmclean.models.SearchType
import uk.co.mgntech.lastfmclean.repositories.SearchRepository

class SearchViewModel : ViewModel() {

    private val _repository = SearchRepository.instance

    fun artistsLoading() = _repository.artistsLoading()
    fun albumsLoading() = _repository.albumsLoading()
    fun songsLoading() = _repository.songsLoading()

    fun artists() = _repository.artists()
    fun albums() = _repository.albums()
    fun songs() = _repository.songs()

    fun setSearchTerm(searchTerm: String) {
        _repository.searchApi(searchTerm, SearchType.ALBUMS, 1)
        _repository.searchApi(searchTerm, SearchType.ARTISTS, 1)
        _repository.searchApi(searchTerm, SearchType.SONGS, 1)
    }
}
