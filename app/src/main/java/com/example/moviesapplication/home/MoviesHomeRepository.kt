package com.example.moviesapplication.home

import com.example.moviesapplication.data.MoviesApi
import com.example.moviesapplication.model.Movies
import com.example.moviesapplication.model.MoviesApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MoviesHomeRepository(private val moviesApi: MoviesApi) {
    suspend fun searchTitle(title: String) : Response<MoviesApiResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.findTitle(title)
        }
    }

    suspend fun getMostPopularMovies() : List<String> {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getMostPopularMovies()
        }
    }

    suspend fun getMovieDetails(id: String): Movies {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getDetails(id)
        }
    }
}