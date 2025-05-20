package com.ceyda.androidbootcampodev.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ceyda.androidbootcampodev.todoapp.data.entity.ToDo
import com.ceyda.androidbootcampodev.todoapp.databinding.ItemTodoBinding
import com.ceyda.androidbootcampodev.todoapp.ui.fragment.HomeFragmentDirections
import com.ceyda.androidbootcampodev.todoapp.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class ToDoAdapter(
    private val context: Context,
    private val todoList: List<ToDo>,
    private val viewModel: HomeViewModel
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    
    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false)
        return ToDoViewHolder(binding)
    }
    
    override fun getItemCount(): Int = todoList.size
    
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoList[position]
        val binding = holder.binding
        
        binding.textViewTodoName.text = todo.name
        
        binding.cardViewTodo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(todo)
            Navigation.findNavController(it).navigate(action)
        }
        
        binding.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "Delete ${todo.name}?", Snackbar.LENGTH_LONG)
                .setAction("YES") {
                    viewModel.deleteToDo(todo.id, todo.name)
                }.show()
        }
    }
}
