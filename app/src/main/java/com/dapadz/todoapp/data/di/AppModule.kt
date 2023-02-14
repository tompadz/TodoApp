package com.dapadz.todoapp.data.di

import android.content.Context
import androidx.room.Room
import com.dapadz.todoapp.data.db.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TaskDataBase::class.java,
        "task_database"
    ).build()


    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDataBase) = db.taskDao()
}