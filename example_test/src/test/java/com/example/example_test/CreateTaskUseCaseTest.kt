package com.example.example_test

import com.example.example_test.model.TaskStatus
import com.example.example_test.repository.TaskRepository
import com.example.example_test.repository.TaskRepositoryImpl
import com.example.example_test.use_case.CreateTaskUseCase
import com.example.example_test.use_case.NewTaskData
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/06/22 11:36 AM
 * @desc
 */
class CreateTaskUseCaseTest {
    private val taskRepository: TaskRepository = TaskRepositoryImpl()
    private val createTaskUseCase = CreateTaskUseCase(taskRepository)

    @Test
    fun `create task`() {
        val title = "title"
        val description = "description"
        val taskData = NewTaskData(
            title = title,
            description = description
        )

        val actual = createTaskUseCase.execute(taskData)

        assertThat(actual.title, equalTo(title))
        assertThat(actual.description, equalTo(description))
        assertThat(actual.status, equalTo(TaskStatus.TODO))
        assertThat(actual.assignee, equalTo(null))
    }
}