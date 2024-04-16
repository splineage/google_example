package com.example.example_test.repository

import com.example.example_test.model.User
import com.example.example_test.model.UserId
import java.util.UUID

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:28 AM
 * @desc
 */
data class UserEntity(
    val id: UUID,
    val name: String,
    val email: String
)


fun UserEntity.toUser() = User(
    id = UserId(id),
    name = name,
    email = email
)

fun User.toUserEntity() = UserEntity(
    id = id.id,
    name = name,
    email = email
)