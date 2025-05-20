package com.ceyda.androidbootcampodev.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
