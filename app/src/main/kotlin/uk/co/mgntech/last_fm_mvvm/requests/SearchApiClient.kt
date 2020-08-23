package uk.co.mgntech.last_fm_mvvm.requests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import uk.co.mgntech.last_fm_mvvm.models.Search
import uk.co.mgntech.last_fm_mvvm.models.SearchType

class SearchApiClient {

    companion object {
        val instance = SearchApiClient()
        private const val TAG = "SearchApiClient"
    }

    val artistsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val albumsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val songsLoading: MutableLiveData<Boolean> = MutableLiveData()

    val artists: MutableLiveData<List<Search>> = MutableLiveData()
    val albums: MutableLiveData<List<Search>> = MutableLiveData()
    val songs: MutableLiveData<List<Search>> = MutableLiveData()

    fun searchApi(query: String, type: SearchType): Disposable {
        startLoading(type)

        val searchFlowable = when (type) {
            SearchType.ALBUMS -> ServiceGenerator.lastFMApi.searchAlbum(query)
            SearchType.ARTISTS -> ServiceGenerator.lastFMApi.searchArtist(query)
            SearchType.SONGS -> ServiceGenerator.lastFMApi.searchSong(query)
        }

        return searchFlowable
            .map { it.results() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                updateResults(type, it)
            }, {
                stopLoading(type)
                Log.w(TAG, "searchApi: ", it)
            }, {
                stopLoading(type)
            })
    }

    private fun updateResults(
        type: SearchType,
        it: List<Search>?
    ) {
        when (type) {
            SearchType.ALBUMS -> albums.postValue(it)
            SearchType.ARTISTS -> artists.postValue(it)
            SearchType.SONGS -> songs.postValue(it)
        }
    }

    private fun startLoading(type: SearchType) {
        when (type) {
            SearchType.ALBUMS -> albumsLoading.postValue(true)
            SearchType.ARTISTS -> artistsLoading.postValue(true)
            SearchType.SONGS -> songsLoading.postValue(true)
        }
    }

    private fun stopLoading(type: SearchType) {
        when (type) {
            SearchType.ALBUMS -> albumsLoading.postValue(false)
            SearchType.ARTISTS -> artistsLoading.postValue(false)
            SearchType.SONGS -> songsLoading.postValue(false)
        }
    }
}
