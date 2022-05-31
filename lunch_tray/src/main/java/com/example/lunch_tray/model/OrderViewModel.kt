package com.example.lunch_tray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunch_tray.data.DataSource
import timber.log.Timber
import java.text.NumberFormat

class OrderViewModel: ViewModel() {

    val menuItems = DataSource.menuItems

    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    private val taxRate = 0.08

    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> get() = _entree

    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total){
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax){
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun getEntreePrice(): String{
        if (_entree.value == null) return ""
        return NumberFormat.getCurrencyInstance().format(_entree.value?.price)
    }

    fun getSidePrice(): String{
        if (_side.value == null) return ""
        return NumberFormat.getCurrencyInstance().format(_side.value?.price)
    }

    fun getAccompanimentPrice(): String{
        if (_accompaniment.value == null) return ""
        return NumberFormat.getCurrencyInstance().format(_accompaniment.value?.price)
    }

    fun setEntree(entree: String){
        Timber.d(entree)
        if (_entree.value != null){
            previousEntreePrice = _entree.value!!.price
        }
        if (_subtotal.value != 0.0){
            _subtotal.value = _subtotal.value!! - previousEntreePrice
        }
        previousEntreePrice = menuItems[entree]!!.price
        _entree.value = menuItems[entree]
        _entree.value?.price?.let { updateSubtotal(it) }
    }

    fun setSide(side: String){
        if (_side.value != null){
            previousSidePrice = _side.value!!.price
        }
        if (_subtotal.value != 0.0){
            _subtotal.value = _subtotal.value!! - previousSidePrice
        }
        previousSidePrice = menuItems[side]!!.price
        _side.value = menuItems[side]
        _side.value?.price?.let { updateSubtotal(it) }
    }

    fun setAccompaniment(accompaniment: String){
        if (_accompaniment.value != null){
            previousAccompanimentPrice = _accompaniment.value!!.price
        }
        if (_subtotal.value != 0.0){
            _subtotal.value = _subtotal.value!! - previousAccompanimentPrice
        }
        previousAccompanimentPrice = menuItems[accompaniment]!!.price
        _accompaniment.value = menuItems[accompaniment]
        _accompaniment.value?.price?.let { updateSubtotal(it) }
    }

    private fun updateSubtotal(itemPrice: Double){
        if (_subtotal.value != 0.0){
            _subtotal.value = _subtotal.value!! + itemPrice
        }else{
            _subtotal.value = itemPrice
        }
        calculateTaxAndTotal()
    }

    fun calculateTaxAndTotal(){
        val total = previousEntreePrice + previousSidePrice + previousAccompanimentPrice
        _tax.value = total * taxRate
        _total.value = total + (total * taxRate)
    }

    fun resetOrder(){
        _tax.value = 0.0
        _total.value = 0.0
        _subtotal.value = 0.0
        _entree.value = null
        _side.value = null
        _accompaniment.value = null
        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0
    }
}