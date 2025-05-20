package com.ceyda.androidbootcampodev.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.ceyda.androidbootcampodev.todoapp.databinding.FragmentDetailBinding
import com.ceyda.androidbootcampodev.todoapp.ui.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up toolbar navigation
        binding.toolbarDetail.setNavigationOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        // Get the todo from arguments
        val todo = args.todo

        // Set the current todo name in the edit text
        binding.editTextTodoName.setText(todo.name)

        // Set up update button click listener
        binding.buttonUpdate.setOnClickListener {
            val updatedName = binding.editTextTodoName.text.toString()
            
            if (updatedName.isBlank()) {
                Snackbar.make(it, "Task name cannot be empty", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            viewModel.updateToDo(todo.id, updatedName)
            
            Snackbar.make(it, "Task updated successfully", Snackbar.LENGTH_SHORT).show()
            
            // Navigate back to home fragment
            Navigation.findNavController(it).popBackStack()
        }
    }
}
