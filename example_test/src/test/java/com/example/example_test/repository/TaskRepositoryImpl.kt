package com.example.example_test.repository

import com.example.example_test.model.Task
import com.example.example_test.model.TaskId

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:26 AM
 * @desc
 */
class TaskRepositoryImpl: TaskRepository {
    private val tasks = mutableListOf<TaskEntity>()

    override fun create(task: Task): Task {
        tasks.add(task.toTaskEntity())
        return task
    }

    override fun update(task: Task): Task {
        val existingTask = getById(task.id)
        existingTask?.let {
            tasks.add(task.toTaskEntity())
        }
        return task
    }

    override fun getById(taskId: TaskId): Task? {
        return tasks.find { it.id == taskId.id }?.toTask()
    }

    override fun getAll(): List<Task> {
        return tasks.map { it.toTask() }
    }
}
