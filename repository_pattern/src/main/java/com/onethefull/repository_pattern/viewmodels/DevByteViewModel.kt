package com.onethefull.repository_pattern.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.onethefull.repository_pattern.database.getDatabase
import com.onethefull.repository_pattern.domain.DevByteVideo
import com.onethefull.repository_pattern.network.DevByteNetwork
import com.onethefull.repository_pattern.network.asDomainModel
import com.onethefull.repository_pattern.repository.VideosRepository
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 2:28 오후
 * @desc
 */
class DevByteViewModel(application: Application): AndroidViewModel(application) {
//    private val _playlist = MutableLiveData<List<DevByteVideo>>()
//    val playlist: LiveData<List<DevByteVideo>> get() = _playlist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown

    private val videosRepository = VideosRepository(getDatabase(application))

    val playlist = videosRepository.videos

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
//        refreshDataFromNetwork()
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                videosRepository.refreshVideos()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            }catch (networkError: IOException){
                if (playlist.value.isNullOrEmpty()){
                    _eventNetworkError.value = true
                }
            }
        }
    }

    // change refreshDataFromRepository
//    private fun refreshDataFromNetwork() = viewModelScope.launch {
//        try {
//            val playlist = DevByteNetwork.devbytes.getPlaylist()
//            _playlist.postValue(playlist.asDomainModel())
//            _eventNetworkError.value = false
//            _isNetworkErrorShown.value = false
//        }catch (networkError: IOException){
//            // Show a Toast error message and hide the progress bar.
//            _eventNetworkError.value = true
//        }
//    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown(){
        _isNetworkErrorShown.value = true
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevByteViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}