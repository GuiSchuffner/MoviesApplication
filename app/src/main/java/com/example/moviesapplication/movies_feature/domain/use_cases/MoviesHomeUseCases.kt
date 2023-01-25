package com.example.moviesapplication.movies_feature.domain.use_cases

data class MoviesHomeUseCases(
    val getPopularMoviesIdsUseCase: GetPopularMoviesIdsUseCase,
    val searchTitleUseCase: SearchTitleUseCase,
    val getMovieUseCase: GetMovieUseCase
)
