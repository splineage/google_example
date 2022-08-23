package com.test.android_hilt.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:15 오전
 * @desc
 */

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun logDao(): LogDao
}