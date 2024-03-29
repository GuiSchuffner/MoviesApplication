package com.example.moviesapplication.movies_feature.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.domain.use_cases.MoviesHomeUseCases
import kotlinx.coroutines.launch

class MoviesHomeViewModel(
    private val moviesHomeUseCases: MoviesHomeUseCases
): ViewModel() {

    var inputMovies by mutableStateOf("")
        private set

    private var showSearchList = false

    var currentPage by mutableStateOf(1)
        private set

    private val pageSize=10
    private var numberOfPages = 10

    private var currentMaxIndex = 0

    var loading by mutableStateOf(false)
        private set

    private var topMoviesIds = listOf<String>()

    private var moviesList = listOf<Movies>()

    var moviesPage by mutableStateOf<List<Movies>>(listOf())
        private set

    private val topMoviesList = mutableListOf<Movies>()

    var moviesListLabel by mutableStateOf("Most Popular Movies")
        private set

    var pagesNumberList by mutableStateOf<List<Int>>(listOf())
        private set

    init {
        //getMostPopularMovies()
    }

    fun onInputMoviesChanged(movie: String) {
        inputMovies = movie
    }

    fun onSearch(){
        loading = true
        viewModelScope.launch {
            try{
                //TODO: hande empty moviesResponse
                moviesList = moviesHomeUseCases.searchTitleUseCase(inputMovies)!!.results
                numberOfPages = moviesList.size / 10
                if(moviesList.size % 10 > 0 ) numberOfPages++
                if(numberOfPages > 10)
                    numberOfPages = 10 // max number of pages
                setListOfNumberOfPages()
                setSearchMoviesPage(1)
            } catch (e : Exception) {
                // TODO: handle exceptions
            }
            moviesListLabel = "Search: $inputMovies"
            loading = false
            showSearchList = true
        }
    }

    private fun setListOfNumberOfPages() {
        val list = mutableListOf<Int>()
        for(i in 1..numberOfPages) {
            list.add(i)
        }
        pagesNumberList = list
    }

    fun onPageSelected(page: Int) {
        if(showSearchList)
            setSearchMoviesPage(page)
        else
            setMostPopularMoviesPage(page)
    }

    fun getFilteredMovieId(movie: Movies): String {
        val regex = "(tt\\w+)".toRegex()
        val filteredMovieId = regex.find(movie.id)
        return filteredMovieId?.groups?.get(0)?.value ?: ""
    }

    private fun setSearchMoviesPage(page: Int) {
        if(page in 1..numberOfPages) {
            val startIndex = (page-1)*pageSize
            val endIndex = page*pageSize - 1
            moviesPage = ArrayList(moviesList.subList(startIndex, endIndex+1))
            currentPage = page
        }
        else {
            // TODO: handle exception
        }
    }

    private fun setMostPopularMoviesPage(page: Int) {
        loading = true
        viewModelScope.launch {
            try{
                getMostPopularMoviesPage(page)
            } catch(e: Exception) {
                //todo
            }
            loading = false
        }
    }

    private fun getMostPopularMovies() {
        loading = true
        viewModelScope.launch {
            try{
                topMoviesIds = moviesHomeUseCases.getPopularMoviesIdsUseCase()
                getMostPopularMoviesPage(1)
                setListOfNumberOfPages()
            } catch (e: Exception) {
                // to do
            }
            loading = false
        }
    }

    private suspend fun getMostPopularMoviesPage(page: Int) {
        if(page in 1..numberOfPages) {
            var startIndex = (page-1)*pageSize
            val endIndex = page*pageSize-1
            if(endIndex>currentMaxIndex) {
                if(startIndex>currentMaxIndex) {
                    startIndex = currentMaxIndex+1
                }
                val moviesIdsList = ArrayList(topMoviesIds.subList(startIndex, endIndex+1))
                topMoviesList.addAll(moviesHomeUseCases.getMoviesUseCase(moviesIdsList))
                currentMaxIndex=endIndex
                startIndex = (page-1)*pageSize
            }
            moviesPage = ArrayList(topMoviesList.subList(startIndex, endIndex+1))
            currentPage = page
        }
        else {
            //TODO: throw or handle exception
        }
    }
}
