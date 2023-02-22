package com.example.moviesapplication.movies_feature.di

import com.example.moviesapplication.movies_feature.data.data_source.MoviesApi
import com.example.moviesapplication.movies_feature.presentation.home.MoviesHomeViewModel
import com.example.moviesapplication.movies_feature.presentation.moviedetails.MovieDetailsViewModel
import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository
import com.example.moviesapplication.movies_feature.domain.repository.MoviesRepositoryImpl
import com.example.moviesapplication.movies_feature.domain.use_cases.*
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val module = module {
    viewModel {
        MoviesHomeViewModel(get())
    }
    viewModel{
        MovieDetailsViewModel(get(), get())
    }
    factory {
        MoviesApi.create()
    }
    single<MoviesRepository>{
        MoviesRepositoryImpl(get())
    }
    single{
        MoviesHomeUseCases(
            GetPopularMoviesIdsUseCase(get()),
            SearchTitleUseCase(get()),
            GetMoviesUseCase(get())
        )
    }
    single{
        GetMovieOverviewUseCase(get())
    }
}
