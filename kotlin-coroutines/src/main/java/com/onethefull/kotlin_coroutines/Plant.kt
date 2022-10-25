package com.onethefull.kotlin_coroutines

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 9:44 오전
 * @desc
 */

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
){
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString(): String {
        return name
    }
}

inline class GrowZone(val number: Int)
val NoGrowZone = GrowZone(-1)