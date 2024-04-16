package com.example.example_test.model

import java.util.UUID

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:23 AM
 * @desc
 */
data class User(
    val id: UserId,
    val name: String,
    val email: String
)

data class UserId(
    val id: UUID
)