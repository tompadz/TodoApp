package com.dapadz.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dapadz.todoapp.data.dao.TaskDao
import com.dapadz.todoapp.data.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}