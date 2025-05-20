package com.ceyda.androidbootcampodev.todoapp.data.repo

import com.ceyda.androidbootcampodev.todoapp.data.datasource.ToDoDataSource
import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDataSource: ToDoDataSource) {
    
    suspend fun getAllToDos(): List<ToDo> = toDoDataSource.getAllToDos()
    
    suspend fun searchToDos(searchQuery: String): List<ToDo> = toDoDataSource.searchToDos(searchQuery)
    
    suspend fun addToDo(name: String) = toDoDataSource.addToDo(name)
    
    suspend fun updateToDo(id: Int, name: String) = toDoDataSource.updateToDo(id, name)
    
    suspend fun deleteToDo(id: Int, name: String) = toDoDataSource.deleteToDo(id, name)
}
