package com.ceyda.androidbootcampodev.tvtimeproto.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ceyda.androidbootcampodev.tvtimeproto.R
import com.ceyda.androidbootcampodev.tvtimeproto.adapter.ContentAdapter
import com.ceyda.androidbootcampodev.tvtimeproto.data.datasource.ContentDataSource
import com.ceyda.androidbootcampodev.tvtimeproto.data.repo.ContentRepository
import com.ceyda.androidbootcampodev.tvtimeproto.di.AppModule
import com.ceyda.androidbootcampodev.tvtimeproto.model.Movie
import com.ceyda.androidbootcampodev.tvtimeproto.model.TvShow
import com.ceyda.androidbootcampodev.tvtimeproto.viewmodel.ContentViewModel
import com.ceyda.androidbootcampodev.tvtimeproto.viewmodel.ContentViewModelFactory

class ContentListFragment : Fragment() {

    private lateinit var viewModel: ContentViewModel
    private lateinit var tvShowsAdapter: ContentAdapter<TvShow>
    private lateinit var moviesAdapter: ContentAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        setupAdapters()

        setupRecyclerViews(view)

        setupSearch(view)

        observeViewModel(view)
    }
    
    private fun setupViewModel() {
        val dataSource = ContentDataSource()
        val repository = ContentRepository(dataSource)
        val factory = ContentViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[ContentViewModel::class.java]
    }
    
    private fun setupSearch(view: View) {
        view.findViewById<SearchView>(R.id.searchView)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchContent(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.loadContent()
                }
                return true
            }
        })
    }

    private fun setupAdapters() {
        tvShowsAdapter = ContentAdapter(emptyList())
        moviesAdapter = ContentAdapter(emptyList())
    }

    private fun setupRecyclerViews(view: View) {
        view.findViewById<RecyclerView>(R.id.tvShowsRecyclerView).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = tvShowsAdapter
        }

        view.findViewById<RecyclerView>(R.id.moviesRecyclerView).apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesAdapter
        }
    }

    private fun observeViewModel(view: View) {
        // Observe loading state
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar?.isVisible = isLoading
        }

        viewModel.tvShows.observe(viewLifecycleOwner) { tvShows ->
            tvShowsAdapter.updateData(tvShows)
            view.findViewById<TextView>(R.id.tvShowsEmptyText)?.isVisible = tvShows.isEmpty()
        }

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.updateData(movies)
            view.findViewById<TextView>(R.id.moviesEmptyText)?.isVisible = movies.isEmpty()
        }
    }
}
