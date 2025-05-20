package com.ceyda.androidbootcampodev.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.ceyda.androidbootcampodev.todoapp.databinding.FragmentAddTodoBinding
import com.ceyda.androidbootcampodev.todoapp.ui.viewmodel.AddToDoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToDoFragment : Fragment() {
    private lateinit var binding: FragmentAddTodoBinding
    private val viewModel: AddToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up toolbar navigation
        binding.toolbarAddTodo.setNavigationOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        // Set up save button click listener
        binding.buttonSave.setOnClickListener {
            val todoName = binding.editTextTodoName.text.toString()
            
            if (todoName.isBlank()) {
                Snackbar.make(it, "Task name cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            viewModel.addToDo(todoName)
            
            Snackbar.make(it, "Task added successfully", Snackbar.LENGTH_SHORT).show()
            
            // Navigate back to home fragment
            Navigation.findNavController(it).popBackStack()
        }
    }
}
