package com.dapadz.todoapp.ui.fragments.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dapadz.todoapp.data.models.Task
import com.dapadz.todoapp.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository : TaskRepository,
) : ViewModel() {

    fun getAll() : Flow<List<Task>> = repository.getAllTasks

    fun removeTask(task : Task) {
        viewModelScope.launch {
            repository.removeTask(task)
        }
    }

    fun createTask(task : Task) {
        viewModelScope.launch {
            repository.createTask(task)
        }
    }
}