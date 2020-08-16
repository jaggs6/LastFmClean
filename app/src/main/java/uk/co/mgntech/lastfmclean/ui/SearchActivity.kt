package uk.co.mgntech.lastfmclean.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.mgntech.lastfmclean.R

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var searchViewModel: SearchViewModel

    companion object {
        private const val TAG = "SearchActivity"
    }

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager
        )
        view_pager.adapter = sectionsPagerAdapter
        view_pager.offscreenPageLimit = 2
        searchView.setIconifiedByDefault(false)
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(view_pager)

        searchView.setOnQueryTextListener(this)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchViewModel.apply {
            setSearchTerm(query.orEmpty())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
