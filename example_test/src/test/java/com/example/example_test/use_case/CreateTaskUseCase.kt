package com.example.example_test.use_case

import com.example.example_test.model.Task
import com.example.example_test.model.TaskId
import com.example.example_test.model.TaskStatus
import com.example.example_test.repository.TaskRepository
import java.util.UUID

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:24 AM
 * @desc
 */
class CreateTaskUseCase(private val taskRepository: TaskRepository) {
    fun execute(taskData: NewTaskData): Task{
        val task = Task(
            id = generateTaskId(),
            title = taskData.title,
            description = taskData.description,
            assignee = null,
            status = TaskStatus.TODO
        )
        return taskRepository.create(task)
    }

    private fun generateTaskId(): TaskId {
        return TaskId(UUID.randomUUID())
    }
}

data class NewTaskData(
    val title: String,
    val description: String
)