package uk.co.mgntech.lastfmclean.ui

import androidx.lifecycle.ViewModel
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
        _repository.searchArtistsApi(searchTerm, 1)
        _repository.searchAlbumsApi(searchTerm, 1)
        _repository.searchSongsApi(searchTerm, 1)
//        _repository.searchArtistsApi(searchTerm, 2)
//        _repository.searchAlbumsApi(searchTerm, 2)
//        _repository.searchSongsApi(searchTerm, 2)
    }
}
