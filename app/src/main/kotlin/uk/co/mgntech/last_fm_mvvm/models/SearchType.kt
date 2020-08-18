package uk.co.mgntech.last_fm_mvvm.models

import uk.co.mgntech.last_fm_mvvm.R

enum class SearchType(val value: Int) {
    ALBUMS(R.string.tab_text_1),
    ARTISTS(R.string.tab_text_2),
    SONGS(R.string.tab_text_3)
}
