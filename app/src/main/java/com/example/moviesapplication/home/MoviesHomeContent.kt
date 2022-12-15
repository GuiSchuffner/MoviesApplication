package com.example.moviesapplication.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapplication.model.Movies
import org.koin.androidx.compose.koinViewModel
import com.example.moviesapplication.R

@Composable
fun MoviesHomeContent(moviesViewModel: MoviesHomeViewModel = koinViewModel()){
    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        MoviesSearch(
            moviesFieldValue = moviesViewModel.inputMovies,
            onValueChanged = { moviesViewModel.onInputMoviesChanged(it) },
            onSearch = { moviesViewModel.onSearch() }
        )
        MoviesList(moviesViewModel.moviesList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesSearch(
    moviesFieldValue: String, onValueChanged: (String)->Unit, onSearch: ()->Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = moviesFieldValue,
        onValueChange = { onValueChanged(it) },
        label = { Text(text = "Search a Movie") },
        modifier = Modifier
            .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp))
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
            focusManager.clearFocus()
        }),
        singleLine = true
    )
}

@Composable
fun MoviesList(moviesList: List<Movies>?) {
    LazyColumn (
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
    ) {
        if(moviesList!=null){
            items(moviesList) { movie ->
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movies){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            if(movie.image!=null){
                AsyncImage(
                    model = movie.image.url,
                    contentDescription = null,
                    modifier = Modifier.size(112.dp)
                )
            }
            else {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    modifier = Modifier.size(112.dp),
                    contentDescription = null
                )
            }
            MovieCardText(movie = movie)
        }
    }
}

@Preview
@Composable
fun MovieCardPreview(){
    val movie = Movies(
        title = "Game of Thrones", id = "test", numberOfEpisodes = 70, seriesEndYear = 2011,
        seriesStartYear = 2019, titleType = "Series", year = 2011, principals = listOf(),
        image = null
    )
    MovieCard(movie = movie)
}

@Composable
fun MovieCardText(movie: Movies){
    Column(modifier = Modifier.padding(start = 12.dp)) {
        if(movie.title!=null){
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
        if (movie.seriesStartYear > 0) {
            Text(
                text = "Series start year: ${movie.seriesStartYear}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if(movie.seriesEndYear > 0 ) {
            Text(
                text = "Series end year: ${movie.seriesEndYear}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (movie.seriesEndYear <= 0 && movie.seriesStartYear <= 0 && movie.year > 0) {
            Text(
                text = "Movie year: ${movie.year}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if(movie.numberOfEpisodes > 0) {
            Text(
                text = "Number of episodes: ${movie.numberOfEpisodes}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun PreviewMoviesSearch(){
    MoviesSearch(moviesFieldValue = "", onValueChanged = {  }, onSearch = {})
}
