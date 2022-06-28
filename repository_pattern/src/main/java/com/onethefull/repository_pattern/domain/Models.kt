package com.onethefull.repository_pattern.domain

import com.onethefull.repository_pattern.util.smartTruncate

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 12:37 오전
 * @desc
 */
data class DevByteVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
){
    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}