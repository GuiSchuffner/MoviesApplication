package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository

class GetMovieOverviewUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(movieId: String): MovieOverview {
        return moviesRepository.getMovieOverview(movieId)
    }
}
