package uk.co.mgntech.lastfmclean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _searchTerm = MutableLiveData<String>()
    val text: LiveData<String> = Transformations.map(_searchTerm) {
        "Hello world from section: $it"
    }

    fun setSearchTerm(searchTerm: String) {
        _searchTerm.value = searchTerm
    }
}