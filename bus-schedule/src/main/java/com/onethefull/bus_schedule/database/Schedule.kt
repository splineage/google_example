package com.onethefull.bus_schedule.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Schedule(
    @PrimaryKey
    val id: Int,

    @NonNull
    @ColumnInfo(name = "stop_name")
    val stopName: String,

    @NonNull
    @ColumnInfo(name = "arrival_time")
    val arrivalTime: Int
)

@Dao
interface ScheduleDao{
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): List<Schedule>
}