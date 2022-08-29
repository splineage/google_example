package com.test.forage.model

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:01 오후
 * @desc
 */
data class Forageable(
    val id: Long = 0,
    val name: String,
    val address: String,
    val inSeason: Boolean,
    val notes: String?
)