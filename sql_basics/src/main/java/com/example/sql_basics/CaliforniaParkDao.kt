package com.example.sql_basics

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CaliforniaParkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(parks: List<CaliforniaPark>)
    @Query("SELECT * FROM park")
    fun getAll(): List<CaliforniaPark>
}