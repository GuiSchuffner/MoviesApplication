package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository

class GetPopularMoviesIdsUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(): List<String> {
        return moviesRepository.getMostPopularMoviesId()
    }
}
