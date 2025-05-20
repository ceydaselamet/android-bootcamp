package com.ceyda.androidbootcampodev.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todos")
    suspend fun getAllToDos(): List<ToDo>

    @Insert
    suspend fun addToDo(toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM todos WHERE name LIKE '%' || :searchQuery || '%'")
    suspend fun searchToDo(searchQuery: String): List<ToDo>
}
