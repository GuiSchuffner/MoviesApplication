package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository
import com.example.moviesapplication.movies_feature.domain.model.MoviesApiResponse

class SearchTitleUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(title: String): MoviesApiResponse? {
        return moviesRepository.searchTitle(title)
    }
}
