package com.test.android_hilt.data

import android.database.Cursor
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

    /**
     * Cursor
     * 이 인터페이스는 데이터베이스 쿼리에서 반환된 결과 집합에 대한 임의의 읽기-쓰기 엑세스를 제공합니다.
     */
    @Query("SELECT * FROM logs ORDER BY id DESC")
    fun selectAllLogsCursor(): Cursor

    @Query("SELECT * FROM logs WHERE id = :id")
    fun selectLogById(id: Long): Cursor
}