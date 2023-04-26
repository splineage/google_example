package com.example.stepcountdao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/25 11:11 PM
 * @desc
 */

@Entity
data class StepCount(
    @PrimaryKey
    val id: Int = 0,
    val userId: Int?,
    val date: String?,
    val hour: Int?,
    var steps: Int? = 0
)

@Entity
data class Users(
    val userId: Int,
)