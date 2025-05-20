package com.ceyda.androidbootcampodev.todoapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ceyda.androidbootcampodev.todoapp.R
import com.ceyda.androidbootcampodev.todoapp.databinding.FragmentHomeBinding
import com.ceyda.androidbootcampodev.todoapp.ui.adapter.ToDoAdapter
import com.ceyda.androidbootcampodev.todoapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewTodos.layoutManager = LinearLayoutManager(requireContext())


        viewModel.toDoList.observe(viewLifecycleOwner) { todoList ->
            if (todoList.isEmpty()) {
                binding.textViewEmpty.visibility = View.VISIBLE
                binding.recyclerViewTodos.visibility = View.GONE
            } else {
                binding.textViewEmpty.visibility = View.GONE
                binding.recyclerViewTodos.visibility = View.VISIBLE
                binding.recyclerViewTodos.adapter = ToDoAdapter(requireContext(), todoList, viewModel)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchToDos(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchToDos(newText)
                return true
            }
        })

        binding.fabAddTodo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeFragmentToAddToDoFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadToDos()
    }
}
