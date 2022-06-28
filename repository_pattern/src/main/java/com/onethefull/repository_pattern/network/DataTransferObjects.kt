package com.onethefull.repository_pattern.network

import com.onethefull.repository_pattern.database.DatabaseVideo
import com.onethefull.repository_pattern.domain.DevByteVideo
import com.squareup.moshi.JsonClass

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 1:19 오전
 * @desc
 */

/**
 * VideoHolder holds a list of Videos.
 * {
 *  "videos": []
 *  }
 */
/*
 @JsonClass 는 JSON Object 에 대응되는 class 를 만들 때 그 위에 붙임.
 generateAdapter 는 true 시 codegen 방식으로 직렬화, 역직렬화가 가능해 진다.
 */
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

/**
 * Videos represent a devbyte that can be played
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)

/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDomainModel(): List<DevByteVideo>{
    return videos.map {
        DevByteVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDatabaseModel(): List<DatabaseVideo>{
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}
