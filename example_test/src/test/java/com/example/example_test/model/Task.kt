package com.example.example_test.model

import java.util.UUID

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:21 AM
 * @desc
 */
data class Task(
    val id: TaskId,
    val title: String,
    val description: String,
    val assignee: User?,
    val status: TaskStatus
)

enum class TaskStatus {
    TODO, IN_PROGRESS, DONE
}

data class TaskId(
    val id: UUID
)