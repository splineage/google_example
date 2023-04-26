package com.example.stepcountdao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/25 11:16 PM
 * @desc
 */
class MainViewModel(private val repository: Repository): ViewModel() {
    private var count = 0

    val event : SharedFlow<List<StepCount>>
        get() = repository.eventProductList

    fun getStepTable(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllStepsTable()
        }
    }


//    fun insertStep(){
//        viewModelScope.launch(Dispatchers.IO) {
//            stepCountDao.insert(StepCount(1, 1, "2021-04-25", 1, 100))
//            stepCountDao.insert(StepCount(2, 2, "2021-05-25", 2, 200))
//            stepCountDao.insert(StepCount(3, 3, "2021-06-25", 3, 300))
//            stepCountDao.insert(StepCount(4, 4, "2021-07-25", 4, 400))
//            stepCountDao.insert(StepCount(5, 5, "2021-08-25", 5, 500))
//        }
//    }
}

class MainViewModelFactory(
    private val repository: Repository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
