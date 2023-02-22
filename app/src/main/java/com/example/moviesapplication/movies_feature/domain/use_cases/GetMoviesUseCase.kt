package com.example.moviesapplication.movies_feature.domain.use_cases

import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository
import com.example.moviesapplication.movies_feature.domain.model.Movies

class GetMoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(moviesIds: List<String>): List<Movies> {
        val regex = "(tt\\w+)".toRegex()
        val moviesList = mutableListOf<Movies>()
        for (id in moviesIds) {
            //TODO: handle regex.find equal null
            val filteredId = regex.find(id)!!.value
            moviesList.add(moviesRepository.getMovieDetails(filteredId))
        }
        return moviesList
    }
}