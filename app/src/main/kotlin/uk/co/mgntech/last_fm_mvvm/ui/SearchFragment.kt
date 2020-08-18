package uk.co.mgntech.last_fm_mvvm.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_search.*
import uk.co.mgntech.last_fm_mvvm.R
import uk.co.mgntech.last_fm_mvvm.adapters.OnSearchListener
import uk.co.mgntech.last_fm_mvvm.adapters.SearchRecyclerAdapter
import uk.co.mgntech.last_fm_mvvm.models.Search
import uk.co.mgntech.last_fm_mvvm.models.SearchType

class SearchFragment : Fragment(), OnSearchListener {

    private var searchType: SearchType? = null
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recyclerAdapter: SearchRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchType =
            SearchType.values().find { it.value == arguments?.getInt(ARG_SEARCH_TYPE_VALUE) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        initRecyclerView(root)
        searchViewModel.apply {
            val results: LiveData<List<Search>> = when (searchType) {
                SearchType.ALBUMS -> albums()
                SearchType.ARTISTS -> artists()
                SearchType.SONGS -> songs()
                null -> TODO("Implement new type properly")
            }
            val loading: LiveData<Boolean> = when (searchType) {
                SearchType.ALBUMS -> albumsLoading()
                SearchType.ARTISTS -> artistsLoading()
                SearchType.SONGS -> songsLoading()
                null -> TODO("Implement new type properly")
            }

            results.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    recyclerAdapter.list = it
                }
            })
            loading.observe(viewLifecycleOwner, Observer {
                pb_search.visibility = if (it) View.VISIBLE else View.GONE
            })
        }

        return root
    }

    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.rv_search_results)
        recyclerAdapter = SearchRecyclerAdapter(this)
        rv.adapter = recyclerAdapter
        rv.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.search_span_count))
    }

    companion object {

        private const val ARG_SEARCH_TYPE_VALUE = "search_type_valuex"

        @JvmStatic
        fun newInstance(type: Int): SearchFragment {
            return SearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SEARCH_TYPE_VALUE, type)
                }
            }
        }
    }

    override fun onSearchClick(position: Int) {
        TODO("Not yet implemented")
    }
}
