package com.example.moviesapplication.home

import com.example.moviesapplication.data.MoviesApi
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
}