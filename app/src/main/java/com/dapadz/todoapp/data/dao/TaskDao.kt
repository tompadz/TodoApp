package com.dapadz.todoapp.data.dao

import androidx.room.*
import com.dapadz.todoapp.data.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM table_task ORDER BY createDate DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: Task)

    @Delete
    suspend fun removeTask(task : Task)
}