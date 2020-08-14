package uk.co.mgntech.lastfmclean

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.mgntech.lastfmclean.models.SearchResultsResponse
import uk.co.mgntech.lastfmclean.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    Callback<SearchResultsResponse> {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        view_pager.offscreenPageLimit = 2
        searchView.setIconifiedByDefault(false)
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(view_pager)

        searchView.setOnQueryTextListener(this)
    }

    private fun testRetrofit(query: String) {
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        testRetrofit(query.orEmpty())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
//        testRetrofit(newText)
        return true
    }

    override fun onFailure(call: Call<SearchResultsResponse>, t: Throwable) {
        Log.d(TAG, "onFailure: " + t.message)
    }

    override fun onResponse(
        call: Call<SearchResultsResponse>,
        response: Response<SearchResultsResponse>
    ) {
        response.body()?.let {
            it.getArtists()?.let { it1 ->
                Log.d(TAG, "getArtists: $it1")
            }
            it.getAlbums()?.let { it1 ->
                Log.d(TAG, "getAlbums: $it1")
            }
            it.getSongs()?.let { it1 ->
                Log.d(TAG, "getSongs: $it1")
            }
        }
    }
}