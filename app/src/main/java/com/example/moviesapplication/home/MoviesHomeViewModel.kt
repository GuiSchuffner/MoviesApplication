package com.example.moviesapplication.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.model.Movies
import kotlinx.coroutines.launch

class MoviesHomeViewModel(
    private val moviesHomeRepository: MoviesHomeRepository
): ViewModel() {

    var inputMovies by mutableStateOf("")
        private set

    private var showSearchList = false

    var currentPage by mutableStateOf(1)
        private set

    private val pageSize=10
    private var numberOfPages = 10

    private var currentMaxIndex = 0;

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
        getMostPopularMovies()
    }

    fun onInputMoviesChanged(movie: String) {
        inputMovies = movie
    }

    fun onSearch(){
        loading = true
        viewModelScope.launch {
            try{
                val response = moviesHomeRepository.searchTitle(inputMovies)
                moviesList = response.body()!!.results
                numberOfPages = moviesList.size / 10
                if(moviesList.size % 10 > 0 ) numberOfPages++
                if(numberOfPages > 10)
                    numberOfPages = 10 // max number of pages
                setListOfNumberOfPages()
                setSearchMoviesPage(1)
            } catch (e : Exception) {
                // TO DO
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

    private fun setSearchMoviesPage(page: Int) {
        if(page in 1..numberOfPages) {
            val startIndex = (page-1)*pageSize
            val endIndex = page*pageSize - 1
            moviesPage = moviesList.subList(startIndex, endIndex+1)
            currentPage = page
        }
    }

    private fun setMostPopularMoviesPage(page: Int) {
        loading = true
        viewModelScope.launch {
            try{
                getMostPopularMoviesPage(page)
            } catch(e: Exception) {
                //to do
            }
            loading = false
        }
    }

    private fun getMostPopularMovies() {
        loading = true
        viewModelScope.launch {
            try{
                topMoviesIds = moviesHomeRepository.getMostPopularMovies()
                getMostPopularMoviesPage(1)
                loading = false
            } catch (e: Exception) {
                //to do
            }
        }
    }

    private suspend fun getMostPopularMoviesPage(page: Int) {
        if(page in 1..pageSize) {
            var startIndex = (page-1)*pageSize
            val endIndex = page*pageSize-1
            if(startIndex>currentMaxIndex) {
                startIndex = currentMaxIndex+1
            }

            val regex = "(tt\\w+)".toRegex()

            for(i: Int in startIndex..endIndex) {
                try{
                    val match = regex.find(topMoviesIds[i])
                    if(match !=null) {
                        getMoviesDetails(match.value)
                    }
                } catch (e: Exception) {

                }

            }
            currentMaxIndex=endIndex
            startIndex = (page-1)*pageSize
            moviesPage = topMoviesList.subList(startIndex, endIndex+1)
            currentPage = page
        }
    }

    private suspend fun getMoviesDetails(id : String) {
        val movie = moviesHomeRepository.getMovieDetails(id)
        topMoviesList.add(movie)
    }
}
