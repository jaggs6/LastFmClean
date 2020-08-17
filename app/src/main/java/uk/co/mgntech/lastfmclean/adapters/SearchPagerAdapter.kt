package uk.co.mgntech.lastfmclean.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uk.co.mgntech.lastfmclean.models.SearchType
import uk.co.mgntech.lastfmclean.ui.SearchFragment

class SearchPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return SearchFragment.newInstance(SearchType.values()[position].value)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(SearchType.values()[position].value)
    }

    override fun getCount(): Int {
        return SearchType.values().size
    }
}
