package com.test.forage.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 4:59 오후
 * @desc
 */
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): ForageableDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "forageable_database"
                ).createFromAsset("database/forageable_database.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}