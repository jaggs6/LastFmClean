package uk.co.mgntech.lastfmclean.requests

import android.util.Log
import androidx.lifecycle.MutableLiveData
import java.io.IOException
import java.util.concurrent.TimeUnit
import uk.co.mgntech.lastfmclean.AppExecutors
import uk.co.mgntech.lastfmclean.models.Album
import uk.co.mgntech.lastfmclean.models.Artist
import uk.co.mgntech.lastfmclean.models.Song
import uk.co.mgntech.lastfmclean.utils.Constants

class SearchApiClient {

    companion object {
        val instance = SearchApiClient()
    }

    val artistsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val albumsLoading: MutableLiveData<Boolean> = MutableLiveData()
    val songsLoading: MutableLiveData<Boolean> = MutableLiveData()

    val artists: MutableLiveData<List<Artist>> = MutableLiveData()
    val albums: MutableLiveData<List<Album>> = MutableLiveData()
    val songs: MutableLiveData<List<Song>> = MutableLiveData()

    fun searchArtistsApi(artist: String, pageNumber: Int) {
        val handler =
            AppExecutors.instance.networkIO.submit(SearchArtistRunnable(artist, pageNumber))

        AppExecutors.instance.networkIO.schedule({
            // TODO: show user timed out
            handler.cancel(true)
            artistsLoading.postValue(false)
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    fun searchAlbumsApi(album: String, pageNumber: Int) {
        val handler =
            AppExecutors.instance.networkIO.submit(SearchAlbumRunnable(album, pageNumber))

        AppExecutors.instance.networkIO.schedule({
            // TODO: show user timed out
            handler.cancel(true)
            albumsLoading.postValue(false)
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    fun searchSongsApi(song: String, pageNumber: Int) {
        val handler =
            AppExecutors.instance.networkIO.submit(SearchSongRunnable(song, pageNumber))

        AppExecutors.instance.networkIO.schedule({
            // TODO: show user timed out
            handler.cancel(true)
            songsLoading.postValue(false)
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    private class SearchArtistRunnable(
        private var artist: String,
        private var pageNumber: Int
    ) : Runnable {

        var cancelRequest: Boolean = false

        override fun run() {
            instance.artistsLoading.postValue(true)
            try {
                val response = ServiceGenerator.lastFMApi.searchArtist(artist, pageNumber).execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val artistList = response.body()?.artists()
                    if (pageNumber == LastFMApi.START_PAGE) {
                        instance.artists.postValue(artistList)
                    } else {
                        instance.artists.postValue(artistList?.let { instance.artists.value?.plus(it) })
                    }
                } else {
                    Log.e(TAG, "run:" + response.errorBody())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            instance.artistsLoading.postValue(false)
        }

        companion object {
            private const val TAG = "SearchArtistRunnable"
        }
    }

    private class SearchAlbumRunnable(
        private var album: String,
        private var pageNumber: Int
    ) : Runnable {

        var cancelRequest: Boolean = false

        override fun run() {
            instance.albumsLoading.postValue(true)
            try {
                val response = ServiceGenerator.lastFMApi.searchAlbum(album, pageNumber).execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val albumsList = response.body()?.albums()
                    if (pageNumber == LastFMApi.START_PAGE) {
                        instance.albums.postValue(albumsList)
                    } else {
                        instance.albums.postValue(albumsList?.let { instance.albums.value?.plus(it) })
                    }
                } else {
                    Log.e(TAG, "run:" + response.errorBody())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            instance.albumsLoading.postValue(false)
        }

        companion object {
            private const val TAG = "SearchAlbumRunnable"
        }
    }

    private class SearchSongRunnable(
        private var song: String,
        private var pageNumber: Int
    ) : Runnable {

        var cancelRequest: Boolean = false

        override fun run() {
            instance.songsLoading.postValue(true)
            try {
                val response = ServiceGenerator.lastFMApi.searchSong(song, pageNumber).execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val songsList = response.body()?.songs()
                    if (pageNumber == LastFMApi.START_PAGE) {
                        instance.songs.postValue(songsList)
                    } else {
                        instance.songs.postValue(songsList?.let { instance.songs.value?.plus(it) })
                    }
                } else {
                    Log.e(TAG, "run:" + response.errorBody())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            instance.songsLoading.postValue(false)
        }

        companion object {
            private const val TAG = "SearchSongRunnable"
        }
    }
}
