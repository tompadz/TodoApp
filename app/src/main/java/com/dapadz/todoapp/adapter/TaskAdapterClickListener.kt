package com.dapadz.todoapp.adapter

import com.dapadz.todoapp.data.models.Task

interface TaskAdapterClickListener {
    fun onRemoveClick(task: Task)
}