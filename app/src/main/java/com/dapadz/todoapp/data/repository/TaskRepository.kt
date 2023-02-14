package com.dapadz.todoapp.data.repository

import com.dapadz.todoapp.data.dao.TaskDao
import com.dapadz.todoapp.data.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao : TaskDao
) {
    val getAllTasks: Flow<List<Task>> = taskDao.getAllTasks()
    suspend fun createTask(task: Task) = taskDao.createTask(task)
    suspend fun removeTask(task : Task) = taskDao.removeTask(task)
}