package com.example.moviesapplication

import com.example.moviesapplication.data.MoviesApi
import com.example.moviesapplication.home.MoviesHomeRepository
import com.example.moviesapplication.home.MoviesHomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val module = module {
     viewModel {
         MoviesHomeViewModel(get())
     }
     factory {
         MoviesApi.create()
     }
     single {
         MoviesHomeRepository(get())
     }
}
