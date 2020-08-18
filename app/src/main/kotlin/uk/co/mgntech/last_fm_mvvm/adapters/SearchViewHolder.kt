package uk.co.mgntech.last_fm_mvvm.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SearchViewHolder(itemView: View, private val searchListener: OnSearchListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        searchListener.onSearchClick(adapterPosition)
    }
}
