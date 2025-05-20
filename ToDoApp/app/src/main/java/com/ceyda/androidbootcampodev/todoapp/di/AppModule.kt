package com.ceyda.androidbootcampodev.todoapp.di

import android.content.Context
import androidx.room.Room
import com.ceyda.androidbootcampodev.todoapp.data.datasource.ToDoDataSource
import com.ceyda.androidbootcampodev.todoapp.data.repo.ToDoRepository
import com.ceyda.androidbootcampodev.todoapp.room.ToDoDao
import com.ceyda.androidbootcampodev.todoapp.room.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    
    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideToDoDao(toDoDatabase: ToDoDatabase): ToDoDao {
        return toDoDatabase.toDoDao()
    }
    
    @Provides
    @Singleton
    fun provideToDoDataSource(toDoDao: ToDoDao): ToDoDataSource {
        return ToDoDataSource(toDoDao)
    }
    
    @Provides
    @Singleton
    fun provideToDoRepository(toDoDataSource: ToDoDataSource): ToDoRepository {
        return ToDoRepository(toDoDataSource)
    }
}
