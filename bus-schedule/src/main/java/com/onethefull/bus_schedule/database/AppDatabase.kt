package com.onethefull.bus_schedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 1. database 에서 정의되는 항목을 지정
 * 2. 각 DAO class 의 단일 인스턴스 엑세스 권한을 제공
 * 3. database 미리 채우기와 같은 추가 설정을 실행
 */
@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object{
        @Volatile // 잠재적 버그 방지
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).createFromAsset("bus_schedule.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}