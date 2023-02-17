package com.example.moviesapplication.movies_feature.data.repository

import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.domain.model.MoviesApiResponse
import retrofit2.Response

interface MoviesRepository {

    suspend fun searchTitle(title: String) : MoviesApiResponse?

    suspend fun getMostPopularMoviesId() : List<String>

    suspend fun getMovieDetails(id: String): Movies

    suspend fun getMovieOverview(title: String) : MovieOverview
}
