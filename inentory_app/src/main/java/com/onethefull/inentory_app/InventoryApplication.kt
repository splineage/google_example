package com.onethefull.inentory_app

import android.app.Application
import com.onethefull.inentory_app.data.ItemRoomDatabase

class InventoryApplication: Application(){
    val database: ItemRoomDatabase by lazy {
        ItemRoomDatabase.getDatabase(this)
    }
}