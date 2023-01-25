package com.example.moviesapplication.movies_feature.domain.repository

import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.domain.model.MoviesApiResponse
import com.example.moviesapplication.movies_feature.data.data_source.MoviesApi
import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MoviesRepositoryImpl(private val moviesApi: MoviesApi): MoviesRepository {

    override suspend fun searchTitle(title: String): Response<MoviesApiResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.findTitle(title)
        }
    }

    override suspend fun getMostPopularMoviesId(): List<String> {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getMostPopularMovies()
        }
    }

    override suspend fun getMovieDetails(id: String): Movies {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getDetails(id)
        }
    }

    override suspend fun getMovieOverview(title: String): MovieOverview {
        return withContext(Dispatchers.IO) {
            return@withContext moviesApi.getOverviewDetails(title)
        }
    }

}
