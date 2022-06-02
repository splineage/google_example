package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amphibians.network.Amphibian

enum class AmphibiansApiStatus{
    LOADING,ERROR,DONE
}

class AmphibianViewModel: ViewModel() {
    private val _status = MutableLiveData<AmphibiansApiStatus>()
    val status: LiveData<AmphibiansApiStatus> get() = _status


    fun onAmphibianClicked(amphibian: Amphibian){

    }
}