package com.ceyda.androidbootcampodev.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo
import com.ceyda.androidbootcampodev.todoapp.data.repo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {
    
    val toDoList = MutableLiveData<List<ToDo>>()
    val isLoading = MutableLiveData<Boolean>()
    
    init {
        loadToDos()
    }
    
    fun loadToDos() {
        isLoading.value = true
        viewModelScope.launch {
            // Simulate network delay for demonstration
            delay(500)
            val todos = toDoRepository.getAllToDos()
            toDoList.value = todos
            isLoading.value = false
        }
    }
    
    fun searchToDos(searchQuery: String) {
        isLoading.value = true
        viewModelScope.launch {
            // Simulate network delay for demonstration
            delay(300)
            val todos = toDoRepository.searchToDos(searchQuery)
            toDoList.value = todos
            isLoading.value = false
        }
    }
    
    fun deleteToDo(id: Int, name: String) {
        viewModelScope.launch {
            toDoRepository.deleteToDo(id, name)
            // Verileri tekrar yüklemeliyiz çünkü artık otomatik güncellenmiyor
            loadToDos()
        }
    }
}
