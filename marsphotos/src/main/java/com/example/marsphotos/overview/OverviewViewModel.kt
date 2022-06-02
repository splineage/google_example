package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos: LiveData<List<MarsPhoto>> get() = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _photos.postValue(MarsApi.retrofitService.getPhotos())
                _status.postValue("Success: Mars properties retrieved")
            }catch (e: Exception){
                _status.postValue("Failure: ${e.message}")
            }

        }
        _status.value = "Set the Mars API status response here!"
    }
}