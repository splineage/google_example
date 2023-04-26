package com.example.stepcountdao

import android.app.Application

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/25 11:15 PM
 * @desc
 */
class App: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}