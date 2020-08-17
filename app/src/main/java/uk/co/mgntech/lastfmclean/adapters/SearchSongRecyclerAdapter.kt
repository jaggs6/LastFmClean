package uk.co.mgntech.lastfmclean.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uk.co.mgntech.lastfmclean.R
import uk.co.mgntech.lastfmclean.models.Song

class SearchSongRecyclerAdapter(
    private val onSearchListener: OnSearchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var songList: List<Song> = listOf()
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
        return songList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = (holder as SearchViewHolder).itemView

        Glide.with(itemView)
            .load(songList[position].imageLarge())
            .into(itemView.findViewById(R.id.cv_image))

        itemView.findViewById<TextView>(R.id.cv_text_name).text =
            "\uD83C\uDFB6️".plus(songList[position].name)

        val additionalTextView = itemView.findViewById<TextView>(R.id.cv_text_additional_name)
        additionalTextView.visibility = View.VISIBLE
        additionalTextView.text =
            "\uD83C\uDF99️".plus(songList[position].artist)

        val listenersTextView = itemView.findViewById<TextView>(R.id.cv_text_listeners)
        listenersTextView.visibility = View.VISIBLE
        listenersTextView.text =
            "\uD83C\uDFA7".plus(songList[position].listeners)
    }
}
