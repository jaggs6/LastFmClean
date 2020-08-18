package uk.co.mgntech.last_fm_mvvm.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.content_scrolling.*
import uk.co.mgntech.last_fm_mvvm.R
import uk.co.mgntech.last_fm_mvvm.models.Search

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data: Search? = intent.getParcelableExtra("data")

        data?.let {

            toolbar_layout.title = it.name

            Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_baseline_radio_24))
                .load(it.imageXLarge())
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(image)
            image.contentDescription = it.name

            it.artist?.let { artist ->
                val additionalTextView = cs_text_additional_name
                additionalTextView.visibility = View.VISIBLE
                additionalTextView.text = getString(R.string.microphone).plus(artist)
            }

            it.listeners?.let { listeners ->
                val listenersTextView = cs_text_listeners
                listenersTextView.visibility = View.VISIBLE
                listenersTextView.text = getString(R.string.headphone).plus(listeners)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
