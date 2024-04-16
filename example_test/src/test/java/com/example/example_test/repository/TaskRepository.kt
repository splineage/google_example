package com.example.example_test.repository

import com.example.example_test.model.Task
import com.example.example_test.model.TaskId

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:24 AM
 * @desc
 */
interface TaskRepository {
    fun create(task: Task): Task
    fun update(task: Task): Task
    fun getById(taskId: TaskId): Task?
    fun getAll(): List<Task>
}