package com.test.forage

import android.app.Application
import com.test.forage.data.AppDatabase

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:47 오후
 * @desc
 */
class BaseApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}