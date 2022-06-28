package com.onethefull.repository_pattern.util

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/06/28 12:39 오전
 * @desc
 */
private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

fun String.smartTruncate(length: Int): String{
    val words = split(" ")
    var added = 0
    var hasMore = false
    var builder = StringBuilder()
    for (word in words){
        if (builder.length > length){
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)){
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore){
        builder.append("...")
    }

    return builder.toString()
}