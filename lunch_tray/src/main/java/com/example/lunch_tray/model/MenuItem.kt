package com.example.lunch_tray.model

import java.text.NumberFormat

data class MenuItem(
    val name: String,
    val description: String,
    val price: Double,
    val type: Int
){
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}