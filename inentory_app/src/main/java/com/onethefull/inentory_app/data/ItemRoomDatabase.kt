package com.onethefull.inentory_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// schema 버전 기록 백업을 유지하지 않음.
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase(){
    abstract fun itemDao(): ItemDao

    // Singleton
    companion object{
        @Volatile
        private var INSTNACE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase{
            return INSTNACE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, // context
                    ItemRoomDatabase::class.java, // database class
                    "item_database" // database name
                )
                    .fallbackToDestructiveMigration() // 이전 경로가 누락되었을 때 기존 데이터를 잃어도 괜찮다면!
                        // 해당 옵션 선택시 Room 이 정의된 이전 경로 없이 이전을 실행하려고 할 때 데이터베이스의 테이블에서
                    // 모든 데이터를 영구적으로 삭제함.
                    .build()
                INSTNACE = instance
                return instance
            }
        }
    }

}