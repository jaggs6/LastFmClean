package uk.co.mgntech.lastfmclean.requests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.io.IOException
import uk.co.mgntech.lastfmclean.AppExecutors
import uk.co.mgntech.lastfmclean.models.Search
import uk.co.mgntech.lastfmclean.models.SearchType

class SearchApiClient {

    companion object {
        val instance = SearchApiClient()
    }

    val artistsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val albumsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val songsLoading: MutableLiveData<Boolean> = MutableLiveData()

    val artists: MutableLiveData<List<Search>> = MutableLiveData()
    val albums: MutableLiveData<List<Search>> = MutableLiveData()
    val songs: MutableLiveData<List<Search>> = MutableLiveData()

    fun searchApi(query: String, type: SearchType, pageNumber: Int) {
        AppExecutors.instance.networkIO.submit(SearchResultsRunnable(query, type, pageNumber))
    }

    private class SearchResultsRunnable(
        private val query: String,
        private val type: SearchType,
        private val pageNumber: Int
    ) : Runnable {

        override fun run() {
            val call = when (type) {
                SearchType.ALBUMS -> ServiceGenerator.lastFMApi.searchAlbum(query, pageNumber)
                SearchType.ARTISTS -> ServiceGenerator.lastFMApi.searchArtist(query, pageNumber)
                SearchType.SONGS -> ServiceGenerator.lastFMApi.searchSong(query, pageNumber)
            }
            val loading = when (type) {
                SearchType.ALBUMS -> instance.albumsLoading
                SearchType.ARTISTS -> instance.artistsLoading
                SearchType.SONGS -> instance.songsLoading
            }
            val results = when (type) {
                SearchType.ALBUMS -> instance.albums
                SearchType.ARTISTS -> instance.artists
                SearchType.SONGS -> instance.songs
            }

            loading.postValue(true)
            val previousValue = results.value
            if (pageNumber == 1) {
                results.postValue(emptyList())
            }
            try {
                val response = call.execute()
                if (response.code() == 200) {
                    val songsList = response.body()?.results()
                    if (pageNumber == LastFMApi.START_PAGE) {
                        results.postValue(songsList)
                    } else {
                        results.postValue(songsList?.let { previousValue?.plus(it) })
                    }
                } else {
                    Log.e(TAG, "run:" + response.errorBody())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            loading.postValue(false)
        }

        companion object {
            private const val TAG = "SearchSongRunnable"
        }
    }
}
