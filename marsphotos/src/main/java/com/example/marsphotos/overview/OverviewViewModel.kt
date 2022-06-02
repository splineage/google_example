package com.example.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class MarsApiStatus{
    LOADING, ERROR, DONE
}
class OverviewViewModel: ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus> get() = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos: LiveData<List<MarsPhoto>> get() = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos(){
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(MarsApiStatus.LOADING)
            try {
                _photos.postValue(MarsApi.retrofitService.getPhotos())
                _status.postValue(MarsApiStatus.DONE)
            }catch (e: Exception){
                _status.postValue(MarsApiStatus.ERROR)
                _photos.postValue(listOf())
            }

        }
    }
}