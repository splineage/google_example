package com.test.forage.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.forage.data.ForageableDao
import com.test.forage.model.Forageable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:57 오후
 * @desc
 */
class ForageableViewModel(val dao: ForageableDao): ViewModel() {
    fun addForageable(
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ){
        val forageable = Forageable(
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
    }

    fun updateForageable(
        id: Long,
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ){
        val forageable = Forageable(
            id = id,
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun deleteForageable(forageable: Forageable){
        viewModelScope.launch(Dispatchers.IO) {  }
    }

    fun isValidEntry(name: String, address: String): Boolean{
        return name.isNotBlank() && address.isNotBlank()
    }
}

class ForageableViewModelFactory(private val dao: ForageableDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForageableViewModel::class.java)){
            return ForageableViewModel(dao) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}