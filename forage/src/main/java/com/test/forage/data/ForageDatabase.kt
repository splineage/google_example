package com.test.forage.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:46 오후
 * @desc
 */
@Database(entities = arrayOf(ForageDatabase::class), version = 1)
data class ForageDatabase(
    val id: Long = 0,
    val name: String,
    val address: String,
    val inSeason: Boolean,
    val notes: String?
)