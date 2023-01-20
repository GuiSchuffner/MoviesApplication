package com.example.moviesapplication

import com.example.moviesapplication.data.MoviesApi
import com.example.moviesapplication.home.MoviesHomeRepository
import com.example.moviesapplication.home.MoviesHomeViewModel
import com.example.moviesapplication.moviedetails.MovieDetailsRepository
import com.example.moviesapplication.moviedetails.MovieDetailsViewModel
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
     single {
         MoviesHomeRepository(get())
     }
    single{
        MovieDetailsRepository(get())
    }
}
