package uk.co.mgntech.lastfmclean.requests

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.mgntech.lastfmclean.models.SearchResultsResponse
import uk.co.mgntech.lastfmclean.utils.Constants

interface LastFMApi {

    companion object {
        const val BASE_PATH = "2.0/"
    }

    @GET(BASE_PATH)
    fun searchArtist(
        @Query("artist") artist: String,
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("format") format: String = Constants.FORMAT,
        @Query("method") method: String = "artist.search"
    ): Call<SearchResultsResponse>

    @GET(BASE_PATH)
    fun searchAlbum(
        @Query("album") album: String,
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("format") format: String = Constants.FORMAT,
        @Query("method") method: String = "album.search"
    ): Call<SearchResultsResponse>


    @GET(BASE_PATH)
    fun searchSong(
        @Query("track") song: String,
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("format") format: String = Constants.FORMAT,
        @Query("method") method: String = "track.search"
    ): Call<SearchResultsResponse>
}