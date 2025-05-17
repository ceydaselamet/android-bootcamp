package com.ceyda.androidbootcampodev.tvtimeproto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceyda.androidbootcampodev.tvtimeproto.data.repo.ContentRepository
import com.ceyda.androidbootcampodev.tvtimeproto.model.Movie
import com.ceyda.androidbootcampodev.tvtimeproto.model.TvShow
import kotlinx.coroutines.launch

class ContentViewModel(private val repository: ContentRepository) : ViewModel() {
    
    private val _tvShows = MutableLiveData<List<TvShow>>()
    val tvShows: LiveData<List<TvShow>> = _tvShows
    
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    init {
        loadContent()
    }
    
    fun loadContent() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _tvShows.value = repository.getTvShows()
                _movies.value = repository.getMovies()
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun searchContent(query: String) {
        if (query.isEmpty()) {
            loadContent()
            return
        }
        
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val (tvShows, movies) = repository.searchContent(query)
                _tvShows.value = tvShows
                _movies.value = movies
            } catch (e: Exception) {
            } finally {
                _isLoading.value = false
            }
        }
    }
}
