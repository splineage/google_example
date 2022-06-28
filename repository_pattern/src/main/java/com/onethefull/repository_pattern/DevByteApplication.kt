package com.onethefull.repository_pattern

import android.app.Application
import timber.log.Timber

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/27 11:56 오후
 * @desc
 */
class DevByteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}