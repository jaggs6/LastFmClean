package uk.co.mgntech.last_fm_mvvm.repositories

import androidx.lifecycle.MutableLiveData
import io.mockk.every
import io.mockk.mockk
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test
import uk.co.mgntech.last_fm_mvvm.models.Search
import uk.co.mgntech.last_fm_mvvm.models.SearchType
import uk.co.mgntech.last_fm_mvvm.requests.SearchApiClient

class SearchRepositoryTest {

    private val searchApiClient: SearchApiClient = mockk()

    private val classUnderTest = SearchRepository(searchApiClient)

    @Test
    fun artistsLoading() {
        every { searchApiClient.artistsLoading } returns MutableLiveData(true)

        assert(classUnderTest.artistsLoading().value!!)
    }

    @Test
    fun albumsLoading() {
        every { searchApiClient.albumsLoading } returns MutableLiveData(true)

        assert(classUnderTest.albumsLoading().value!!)
    }

    @Test
    fun songsLoading() {
        every { searchApiClient.songsLoading } returns MutableLiveData(true)

        assert(classUnderTest.songsLoading().value!!)
    }

    @Test
    fun albums() {
        every { searchApiClient.albums } returns ALBUMS_LIST

        assert(classUnderTest.albums() == ALBUMS_LIST)
    }

    @Test
    fun songs() {
        every { searchApiClient.songs } returns SONGS_LIST

        assert(classUnderTest.songs() == SONGS_LIST)
    }

    @Test
    fun artists() {
        every { searchApiClient.artists } returns ARTISTS_LIST

        assert(classUnderTest.artists() == ARTISTS_LIST)
    }

    @Test
    fun searchApi() {
        every { classUnderTest.searchApi(QUERY, SearchType.ALBUMS) } returns DISPOSABLE

        assert(classUnderTest.searchApi(QUERY, SearchType.ALBUMS) == DISPOSABLE)
    }

    companion object {
        private const val QUERY = "query"
        private val ALBUMS_LIST = MutableLiveData(listOf(Search("firstAlbum", null, null, null)))
        private val ARTISTS_LIST = MutableLiveData(listOf(Search("firstArtist", null, null, null)))
        private val SONGS_LIST = MutableLiveData(listOf(Search("firstSong", null, null, null)))
        private val DISPOSABLE = CompositeDisposable()
    }
}
