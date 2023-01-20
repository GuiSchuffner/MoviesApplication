package com.example.moviesapplication.moviedetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.model.MovieOverview
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    movieDetailsRepository: MovieDetailsRepository,
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
                movieOverview = movieDetailsRepository.getMovieOverview(movieId)
            } catch(_: Exception) {
                //TO DO
            }
            loading = false
        }
    }
}
