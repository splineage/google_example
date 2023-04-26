package com.example.stepcountdao

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/26 12:58 AM
 * @desc
 */
class Repository(private val dao: StepCountDao) {
    private var _eventProductList = MutableSharedFlow<List<StepCount>>()
    val eventProductList = _eventProductList.asSharedFlow()

    private suspend fun eventAllStepTable(allStepTable: List<StepCount>){
        withContext(Dispatchers.Main){
            _eventProductList.emit(allStepTable)
        }
    }

    suspend fun getAllStepsTable() {
        withContext(Dispatchers.IO){
            eventAllStepTable(dao.getAllStepTable().shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Lazily).first())
        }
    }

}