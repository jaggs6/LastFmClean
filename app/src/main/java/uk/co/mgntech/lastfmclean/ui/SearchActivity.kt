package uk.co.mgntech.lastfmclean.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_search.*
import uk.co.mgntech.lastfmclean.R
import uk.co.mgntech.lastfmclean.adapters.SectionsPagerAdapter
import uk.co.mgntech.lastfmclean.utils.hideKeyboard

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchViewModel: SearchViewModel

    companion object {
        private const val TAG = "SearchActivity"
    }

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        vp_search.adapter = sectionsPagerAdapter
        vp_search.offscreenPageLimit = 2
        search_view.setIconifiedByDefault(false)
        val tabs: TabLayout = findViewById(R.id.tabs_search)
        tabs.setupWithViewPager(vp_search)

        search_view.setOnQueryTextListener(this)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideKeyboard()
        searchViewModel.apply {
            setSearchTerm(query.orEmpty())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
