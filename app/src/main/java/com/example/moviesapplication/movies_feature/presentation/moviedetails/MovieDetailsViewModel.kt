package com.example.moviesapplication.movies_feature.presentation.moviedetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.domain.use_cases.GetMovieOverviewUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    getMovieOverviewUseCase: GetMovieOverviewUseCase,
    movieId: String
): ViewModel() {
    var movieOverview by mutableStateOf<MovieOverview?>(null)
        private set
    var loading by mutableStateOf(false)
        private set

    init{
        loading = true
        viewModelScope.launch {
            try{
                movieOverview = getMovieOverviewUseCase(movieId)
            } catch(_: Exception) {
                //TO DO
            }
            loading = false
        }
    }
}
