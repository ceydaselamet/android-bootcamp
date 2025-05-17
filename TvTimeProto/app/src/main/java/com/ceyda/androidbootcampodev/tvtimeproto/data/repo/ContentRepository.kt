package com.ceyda.androidbootcampodev.tvtimeproto.data.repo

import com.ceyda.androidbootcampodev.tvtimeproto.data.datasource.ContentDataSource
import com.ceyda.androidbootcampodev.tvtimeproto.model.Movie
import com.ceyda.androidbootcampodev.tvtimeproto.model.TvShow

class ContentRepository(private val contentDataSource: ContentDataSource) {
    
    suspend fun getTvShows(): List<TvShow> = contentDataSource.getTvShows()
    
    suspend fun getMovies(): List<Movie> = contentDataSource.getMovies()
    
    suspend fun searchContent(query: String): Pair<List<TvShow>, List<Movie>> = 
        contentDataSource.searchContent(query)
}
