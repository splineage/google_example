package com.onethefull.kotlin_coroutines

import androidx.lifecycle.*
import kotlinx.coroutines.Job
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