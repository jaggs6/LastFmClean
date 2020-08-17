package uk.co.mgntech.lastfmclean.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.mgntech.lastfmclean.R
import uk.co.mgntech.lastfmclean.adapters.OnSearchListener
import uk.co.mgntech.lastfmclean.adapters.SearchAlbumRecyclerAdapter
import uk.co.mgntech.lastfmclean.adapters.SearchArtistRecyclerAdapter
import uk.co.mgntech.lastfmclean.adapters.SearchSongRecyclerAdapter
import uk.co.mgntech.lastfmclean.models.Album
import uk.co.mgntech.lastfmclean.models.Artist
import uk.co.mgntech.lastfmclean.models.Song

class SearchFragment : Fragment(), OnSearchListener {

    private var sectionNumber: Int? = null
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var artistRecyclerAdapter: SearchArtistRecyclerAdapter
    private lateinit var albumRecyclerAdapter: SearchAlbumRecyclerAdapter
    private lateinit var songRecyclerAdapter: SearchSongRecyclerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        initRecyclerView(root)
        when (sectionNumber) {
            0 -> searchViewModel.artists().observe(viewLifecycleOwner, Observer<List<Artist>> {
                if (it != null) {
                    artistRecyclerAdapter.artistList = it
                }
            })
            1 -> searchViewModel.albums().observe(viewLifecycleOwner, Observer<List<Album>> {
                if (it != null) {
                    albumRecyclerAdapter.albumList = it
                }
            })
            2 -> searchViewModel.songs().observe(viewLifecycleOwner, Observer<List<Song>> {
                if (it != null) {
                    songRecyclerAdapter.songList = it
                }
            })
        }

        return root
    }

    private fun initRecyclerView(root: View) {
        val rv = root.findViewById<RecyclerView>(R.id.rv_search_results)
        when (sectionNumber) {
            0 -> {
                artistRecyclerAdapter = SearchArtistRecyclerAdapter(this)
                rv.adapter = artistRecyclerAdapter
            }
            1 -> {
                albumRecyclerAdapter = SearchAlbumRecyclerAdapter(this)
                rv.adapter = albumRecyclerAdapter
            }
            2 -> {
                songRecyclerAdapter = SearchSongRecyclerAdapter(this)
                rv.adapter = songRecyclerAdapter
            }
        }
        rv.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.search_span_count))
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(position: Int): SearchFragment {
            return SearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, position)
                }
            }
        }
    }

    override fun onSearchClick(position: Int) {
        TODO("Not yet implemented")
    }
}
