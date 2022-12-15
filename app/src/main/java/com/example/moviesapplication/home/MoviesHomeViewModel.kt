package com.example.moviesapplication.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.model.Movies
import kotlinx.coroutines.launch

class MoviesHomeViewModel(
    private val moviesHomeRepository: MoviesHomeRepository
): ViewModel() {

    var inputMovies by mutableStateOf("")
        private set

    private var _loading by mutableStateOf(false)
    val loading = _loading

    var moviesList by mutableStateOf<List<Movies>>(listOf())

    fun onInputMoviesChanged(movie: String) {
        inputMovies = movie
    }

    fun onSearch(){
        _loading = true
        viewModelScope.launch {
            try{
                val response = moviesHomeRepository.searchTitle(inputMovies)
                moviesList = response.body()!!.results
            } catch (e : Exception) {

            }
            _loading = false
        }
    }
}