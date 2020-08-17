package uk.co.mgntech.lastfmclean.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uk.co.mgntech.lastfmclean.R
import uk.co.mgntech.lastfmclean.models.Search

class SearchRecyclerAdapter(
    private val onSearchListener: OnSearchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var searchResultsList: List<Search> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return SearchViewHolder(view, onSearchListener)
    }

    override fun getItemCount(): Int {
        return searchResultsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = (holder as SearchViewHolder).itemView

        Glide.with(itemView)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_baseline_radio_24))
            .load(searchResultsList[position].imageLarge())
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(itemView.findViewById(R.id.cv_image))

        itemView.findViewById<TextView>(R.id.cv_text_name).text =
            searchResultsList[position].name

        searchResultsList[position].artist?.let {
            val additionalTextView = itemView.findViewById<TextView>(R.id.cv_text_additional_name)
            additionalTextView.visibility = View.VISIBLE
            additionalTextView.text =
                itemView.context.getString(R.string.microphone).plus(it)
        }

        searchResultsList[position].listeners?.let {
            val listenersTextView = itemView.findViewById<TextView>(R.id.cv_text_listeners)
            listenersTextView.visibility = View.VISIBLE
            listenersTextView.text =
                itemView.context.getString(R.string.headphone).plus(it)
        }
    }
}
