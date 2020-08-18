package uk.co.mgntech.last_fm_mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uk.co.mgntech.last_fm_mvvm.R
import uk.co.mgntech.last_fm_mvvm.models.Search

class SearchRecyclerAdapter(
    private val onSearchListener: OnSearchListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<Search> = listOf()
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
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = (holder as SearchViewHolder).itemView

        val image = itemView.findViewById<ImageView>(R.id.cv_image)
        Glide.with(itemView)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_baseline_radio_24))
            .load(list[position].imageLarge())
            .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
            .into(image)
        image.contentDescription = list[position].name

        itemView.findViewById<TextView>(R.id.cv_text_name).text =
            list[position].name

        list[position].artist?.let {
            val additionalTextView = itemView.findViewById<TextView>(R.id.cv_text_additional_name)
            additionalTextView.visibility = View.VISIBLE
            additionalTextView.text =
                itemView.context.getString(R.string.microphone).plus(it)
        }

        list[position].listeners?.let {
            val listenersTextView = itemView.findViewById<TextView>(R.id.cv_text_listeners)
            listenersTextView.visibility = View.VISIBLE
            listenersTextView.text =
                itemView.context.getString(R.string.headphone).plus(it)
        }
    }
}
