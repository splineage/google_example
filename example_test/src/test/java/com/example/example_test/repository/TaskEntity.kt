package com.example.example_test.repository

import com.example.example_test.model.Task
import com.example.example_test.model.TaskId
import com.example.example_test.model.TaskStatus
import java.util.UUID

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:27 AM
 * @desc
 */
data class TaskEntity(
    val id: UUID,
    val title: String,
    val description: String,
    val assignee: UserEntity?,
    val status: TaskStatus
)


fun TaskEntity.toTask() = Task(
    id = TaskId(id),
    title = title,
    description = description,
    assignee = assignee?.toUser(),
    status = status
)

fun Task.toTaskEntity() = TaskEntity(
    id = id.id,
    title = title,
    description = description,
    assignee = assignee?.toUserEntity(),
    status = status
)