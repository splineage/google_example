package com.test.android_hilt.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:16 오전
 * @ Log Data Format
 */

@Entity(tableName = "logs")
data class Log (val msg: String, val timestamp: Long){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}