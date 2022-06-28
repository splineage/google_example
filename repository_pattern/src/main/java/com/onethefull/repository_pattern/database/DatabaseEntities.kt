package com.onethefull.repository_pattern.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onethefull.repository_pattern.domain.DevByteVideo

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 12:34 오전
 * @desc
 */

/**
 * DatabaseVideo represents a video entity in the database.
 */
@Entity
data class DatabaseVideo(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseVideo>.asDomainModel(): List<DevByteVideo>{
    return map{
        DevByteVideo(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}