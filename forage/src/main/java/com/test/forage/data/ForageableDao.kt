package com.test.forage.data

import androidx.room.*
import com.test.forage.model.Forageable
import kotlinx.coroutines.flow.Flow

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:46 오후
 * @desc
 */
@Dao
interface ForageableDao {
    @Query("SELECT * from forageable_database")
    fun getForageables(): Flow<Forageable>
    @Query("SELECT * from forageable_database WHERE id = :id")
    fun getForageable(id: Long): Flow<Forageable>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(forageable: Forageable)
    @Update
    fun update(forageable: Forageable)
    @Delete
    fun delete(forageable: Forageable)
}