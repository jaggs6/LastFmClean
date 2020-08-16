package uk.co.mgntech.lastfmclean.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main.*
import uk.co.mgntech.lastfmclean.R
import uk.co.mgntech.lastfmclean.models.Album
import uk.co.mgntech.lastfmclean.models.Artist
import uk.co.mgntech.lastfmclean.models.Song

class SearchFragment : Fragment() {

    private var sectionNumber: Int? = null
    private lateinit var searchViewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER)
//        searchViewModel.apply {
//            setSearchTerm("hi")
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        when (sectionNumber) {
            0 -> searchViewModel.artists()
                .observe(viewLifecycleOwner, Observer<List<Artist>> {
                    section_label.text = (it.map { artist -> artist.name }).joinToString(separator = "\n")
                })
            1 -> searchViewModel.albums().observe(viewLifecycleOwner, Observer<List<Album>> {
                section_label.text = (it.map { album -> album.name }).joinToString(separator = "\n")
            })
            2 -> searchViewModel.songs().observe(viewLifecycleOwner, Observer<List<Song>> {
                section_label.text = (it.map { song -> song.name }).joinToString(separator = "\n")
            })
        }

        return root
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
}
