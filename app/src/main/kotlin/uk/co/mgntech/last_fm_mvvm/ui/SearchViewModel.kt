package uk.co.mgntech.last_fm_mvvm.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import uk.co.mgntech.last_fm_mvvm.models.SearchType
import uk.co.mgntech.last_fm_mvvm.repositories.SearchRepository

class SearchViewModel : ViewModel() {

    private val _repository = SearchRepository.instance

    fun artistsLoading() = _repository.artistsLoading()
    fun albumsLoading() = _repository.albumsLoading()
    fun songsLoading() = _repository.songsLoading()

    fun artists() = _repository.artists()
    fun albums() = _repository.albums()
    fun songs() = _repository.songs()

    private val disposables = mutableListOf<Disposable>()

    fun setSearchTerm(searchTerm: String) {
        disposables.add(_repository.searchApi(searchTerm, SearchType.ALBUMS))
        disposables.add(_repository.searchApi(searchTerm, SearchType.ARTISTS))
        disposables.add(_repository.searchApi(searchTerm, SearchType.SONGS))
    }

    fun cancel() {
        disposables.forEach {
            it.dispose()
        }
    }
}
