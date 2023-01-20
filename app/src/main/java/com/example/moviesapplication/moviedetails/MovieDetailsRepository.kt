package com.example.moviesapplication.moviedetails

import com.example.moviesapplication.data.MoviesApi
import com.example.moviesapplication.model.MovieOverview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsRepository(private val moviesApi: MoviesApi) {
    suspend fun getMovieOverview(title: String) : MovieOverview {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getOverviewDetails(title)
        }
    }
}
