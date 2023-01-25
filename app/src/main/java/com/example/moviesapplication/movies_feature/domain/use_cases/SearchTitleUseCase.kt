package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository

class SearchTitleUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(title: String): List<Movies> {
        return moviesRepository.searchTitle(title).body()!!.results
    }
}