package com.test.android_hilt

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.test.android_hilt.data.AppDatabase
import com.test.android_hilt.data.LoggerLocalDataSource
import com.test.android_hilt.navigator.AppNavigator
import com.test.android_hilt.navigator.AppNavigatorImpl
import com.test.android_hilt.util.DateFormatter

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:14 오전
 * @desc
 */
class ServiceLocator(applicationContext: Context) {
    private val logsDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "logging.db"
    ).build()

    val loggerLocalDataSource = LoggerLocalDataSource(logsDatabase.logDao())

    fun provideDateFormatter() = DateFormatter()

    fun provideNavigator(activity: FragmentActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }
}