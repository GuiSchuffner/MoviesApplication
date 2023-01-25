package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository

class GetMovieUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(movieId: String): Movies {
        return moviesRepository.getMovieDetails(movieId)
    }
}
