package com.onethefull.kotlin_coroutines

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 10:18 오전
 * @desc
 */
class PlantListViewModel internal constructor(
    private val plantRepository: PlantRepository
): ViewModel() {

    init {
        Log.d("PlantListViewModel", "init")
    }
    private val _snackbar = MutableLiveData<String?>()

    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _spinner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spinner

    private val growZone = MutableLiveData<GrowZone>(NoGrowZone)

    val plants: LiveData<List<Plant>> = growZone.switchMap { growZone ->
        if (growZone == NoGrowZone){
            plantRepository.plants
        }else{
            plantRepository.getPlantsWithGrowZone(growZone)
        }
    }

    private val _testFlow = MutableStateFlow<List<Int>>(arrayListOf(1,2,3))
    val testFlow = _testFlow.asStateFlow()

    fun test(){
        viewModelScope.launch {
//            val list = arrayListOf<Int>(1,2,3,4,5)
            _testFlow.emit(emptyList())
        }
    }

    init {
        clearGrowZoneNumber()
    }

    fun setGrowZoneNumber(num: Int){
        growZone.value = GrowZone(num)
        launchDataLoad { plantRepository.tryUpdateRecentPlantsCache() }
    }

    fun clearGrowZoneNumber(){
        growZone.value = NoGrowZone
        launchDataLoad { plantRepository.tryUpdateRecentPlantsCache() }
    }

    fun isFiltered() = growZone.value != NoGrowZone

    fun onSnackbarShown(){
        _snackbar.value = null
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job{
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            }catch (error: Throwable){
                _snackbar.value = error.message
            }finally {
                _spinner.value = false
            }
        }
    }
}

