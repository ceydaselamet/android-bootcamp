package com.ceyda.androidbootcampodev.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ceyda.androidbootcampodev.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {
    
    fun addToDo(name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            toDoRepository.addToDo(name)
        }
    }
}
