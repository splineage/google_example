package com.example.stepcountdao

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/25 11:10 PM
 * @desc
 */
@Database(entities = [StepCount::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun stepCountDao(): StepCountDao

    companion object{
        @Volatile // 잠재적 버그 방지
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )

                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Dao
interface StepCountDao{
    @Query("SELECT * FROM stepcount")
    fun getAllStepTable(): Flow<List<StepCount>>

    @Insert
    fun insert(stepCount: StepCount)
}