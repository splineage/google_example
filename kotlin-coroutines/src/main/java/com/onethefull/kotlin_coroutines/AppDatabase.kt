package com.onethefull.kotlin_coroutines

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 9:38 오전
 * @desc
 */

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(
                    context
                ).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}

private const val DATABASE_NAME = "sunflower-db"