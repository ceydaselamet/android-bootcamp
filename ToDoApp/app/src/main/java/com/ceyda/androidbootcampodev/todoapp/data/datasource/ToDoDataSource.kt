package com.ceyda.androidbootcampodev.todoapp.data.datasource

import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo
import com.ceyda.androidbootcampodev.todoapp.room.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoDataSource @Inject constructor(private val toDoDao: ToDoDao) {
    
    suspend fun getAllToDos(): List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext toDoDao.getAllToDos()
    }
    
    suspend fun searchToDos(searchQuery: String): List<ToDo> = withContext(Dispatchers.IO) {
        return@withContext toDoDao.searchToDo(searchQuery)
    }
    
    suspend fun addToDo(name: String) = withContext(Dispatchers.IO) {
        val newToDo = ToDo(name = name)
        toDoDao.addToDo(newToDo)
    }
    
    suspend fun updateToDo(id: Int, name: String) = withContext(Dispatchers.IO) {
        val updatedToDo = ToDo(id, name)
        toDoDao.updateToDo(updatedToDo)
    }
    
    suspend fun deleteToDo(id: Int, name: String) = withContext(Dispatchers.IO) {
        val deletedToDo = ToDo(id, name)
        toDoDao.deleteToDo(deletedToDo)
    }
}
