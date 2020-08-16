package uk.co.mgntech.lastfmclean.requests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.mgntech.lastfmclean.utils.Constants

class ServiceGenerator {

    companion object {
        private val retrofitBuilder =
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        val lastFMApi: LastFMApi = retrofit.create(LastFMApi::class.java)
    }
}
