package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class AmphibiansApiStatus{
    LOADING,ERROR,DONE
}

class AmphibianViewModel: ViewModel() {
    private val _status = MutableLiveData<AmphibiansApiStatus>()
    val status: LiveData<AmphibiansApiStatus> get() = _status

    private val _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> get() = _amphibians

    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> get() = _amphibian

    init {
        getAmphibianList()
    }

    private fun getAmphibianList(){
        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(AmphibiansApiStatus.LOADING)
            try {
                _amphibians.postValue(AmphibianApi.retrofitService.getAmphibians())
                _status.postValue(AmphibiansApiStatus.DONE)
            }catch (e: Exception){
                _status.postValue(AmphibiansApiStatus.ERROR)
                _amphibians.postValue(listOf())
            }
        }
    }
    fun onAmphibianClicked(amphibian: Amphibian){
        _amphibian.value = amphibian
    }
}