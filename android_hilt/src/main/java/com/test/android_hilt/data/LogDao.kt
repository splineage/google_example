package com.test.android_hilt.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:18 오전
 * @DB 호출 명령.
 */
@Dao
interface LogDao {
    @Query("SELECT * FROM logs ORDER BY id DESC")
    fun getAll(): List<Log>

    @Insert
    fun insertAll(vararg logs: Log)

    @Query("DELETE FROM logs")
    fun nukeTable()
}