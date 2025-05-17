package com.ceyda.androidbootcampodev.tvtimeproto.data.datasource

import android.util.Log
import com.ceyda.androidbootcampodev.tvtimeproto.model.Movie
import com.ceyda.androidbootcampodev.tvtimeproto.model.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ContentDataSource {
    
    suspend fun getTvShows(): List<TvShow> = withContext(Dispatchers.IO) {
        delay(1000)
        
        val tvShowsList = ArrayList<TvShow>()
        
        // Mock data for TV shows
        tvShowsList.add(TvShow(1, "Breaking Bad", "https://m.media-amazon.com/images/M/MV5BYmQ4YWMxYjUtNjZmYi00MDQ1LWFjMjMtNjA5ZDdiYjdiODU5XkEyXkFqcGdeQXVyMTMzNDExODE5._V1_.jpg"))
        tvShowsList.add(TvShow(2, "Fringe", "https://m.media-amazon.com/images/M/MV5BMjMyOTg0ODk5NV5BMl5BanBnXkFtZTcwNjE2MTQ4Mg@@._V1_.jpg"))
        tvShowsList.add(TvShow(3, "Merlin", "https://m.media-amazon.com/images/M/MV5BZDcwMzU4NWYtODczMy00OTRkLThmNTEtNzE4ODVjNzMyM2ZmXkEyXkFqcGdeQXVyNTE1NjY5Mg@@._V1_.jpg"))
        tvShowsList.add(TvShow(4, "The Office", "https://m.media-amazon.com/images/M/MV5BMDNkOTE4NDQtMTNmYi00MWE0LWE4ZTktYTc0NzhhNWIzNzJiXkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_.jpg"))
        tvShowsList.add(TvShow(5, "Sopranos", "https://m.media-amazon.com/images/M/MV5BZGJjYzhjYTYtMDBjYy00OWU1LTg5OTYtNmYwOTZmZjE3ZDdhXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg"))
        tvShowsList.add(TvShow(6, "Diener", "https://m.media-amazon.com/images/M/MV5BYzI0MjRmOTktY2Y2Yy00YTFiLThkZTMtMzFiNGI1YjBmMjU3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"))
        
        Log.d("ContentDataSource", "Loaded ${tvShowsList.size} TV shows")
        return@withContext tvShowsList
    }
    
    suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) {
        // Simulate network delay
        delay(1000)
        
        val moviesList = ArrayList<Movie>()
        
        // Mock data for movies
        moviesList.add(Movie(1, "Avengers: Endgame", "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg"))
        moviesList.add(Movie(2, "Black Widow", "https://m.media-amazon.com/images/M/MV5BNjRmNDI5MjMtMmFhZi00YzcwLWI4ZGItMGI2MjI0N2Q3YmIwXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"))
        moviesList.add(Movie(3, "Avengers: Age of Ultron", "https://m.media-amazon.com/images/M/MV5BMTM4OGJmNWMtOTM4Ni00NTE3LTg3MDItZmQxYjc4N2JhNmUxXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_.jpg"))
        moviesList.add(Movie(4, "Spider-Man: No Way Home", "https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_.jpg"))
        
        Log.d("ContentDataSource", "Loaded ${moviesList.size} movies")
        return@withContext moviesList
    }
    
    suspend fun searchContent(query: String): Pair<List<TvShow>, List<Movie>> = withContext(Dispatchers.IO) {
        delay(800)
        
        val tvShows = getTvShows().filter { it.title.contains(query, ignoreCase = true) }
        val movies = getMovies().filter { it.title.contains(query, ignoreCase = true) }
        
        Log.d("ContentDataSource", "Search for '$query' found ${tvShows.size} TV shows and ${movies.size} movies")
        return@withContext Pair(tvShows, movies)
    }
}
