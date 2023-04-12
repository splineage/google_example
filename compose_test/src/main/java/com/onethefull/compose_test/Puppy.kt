package com.onethefull.compose_test

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/11 5:10 PM
 * @desc
 */
data class Puppy(
    val id: Int,
    val title: String,
    val sex: String,
    val age: Int,
    val description: String,
    val puppyImageId: Int = 0
): java.io.Serializable