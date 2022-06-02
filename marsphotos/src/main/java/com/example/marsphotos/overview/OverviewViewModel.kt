package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos(){
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.postValue("Success: ${listResult.size} Mars photos retrieved")
            }catch (e: Exception){
                _status.postValue("Failure: ${e.message}")
            }

        }
        _status.value = "Set the Mars API status response here!"
    }
}