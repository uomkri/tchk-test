package com.tchair.tchktest.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

class DetailsViewModel : ViewModel() {

    private val _navigateToDetails = MutableLiveData<String>()
    val navigateToDetails
            get() = _navigateToDetails

    fun onUserClicked(login: String) {
        _navigateToDetails.value = login
    }

    fun onDetailsNavigated() {
        _navigateToDetails.value = null
    }

}