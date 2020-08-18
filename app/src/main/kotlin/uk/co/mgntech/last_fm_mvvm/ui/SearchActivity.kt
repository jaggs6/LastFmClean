package uk.co.mgntech.last_fm_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_search.*
import uk.co.mgntech.last_fm_mvvm.R
import uk.co.mgntech.last_fm_mvvm.adapters.SearchPagerAdapter

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchViewModel: SearchViewModel

    companion object {
        private const val TAG = "SearchActivity"
    }

    private lateinit var sectionsPagerAdapter: SearchPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        sectionsPagerAdapter =
            SearchPagerAdapter(
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
        search_view.clearFocus()
        searchViewModel.apply {
            setSearchTerm(query.orEmpty())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
