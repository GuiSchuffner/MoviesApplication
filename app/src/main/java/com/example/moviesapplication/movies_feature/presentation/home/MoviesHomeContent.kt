package com.example.moviesapplication.movies_feature.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapplication.movies_feature.domain.model.Movies
import org.koin.androidx.compose.koinViewModel
import com.example.moviesapplication.R
import com.example.moviesapplication.movies_feature.domain.model.MovieImage
import com.example.moviesapplication.ui.theme.Navy
import com.example.moviesapplication.ui.theme.Yellow

@Composable
fun MoviesHomeContent(
    navigateToMovieDetails: (String)->Unit,
    moviesViewModel: MoviesHomeViewModel = koinViewModel()
){
    Column {
        MoviesSearch(
            moviesFieldValue = moviesViewModel.inputMovies,
            onValueChanged = { moviesViewModel.onInputMoviesChanged(it) },
            onSearch = { moviesViewModel.onSearch() }
        )
        CurrentListLabelText(text = moviesViewModel.moviesListLabel)
        PageSelector(
            pagesList = moviesViewModel.pagesNumberList,
            onPageSelected = { moviesViewModel.onPageSelected(it) },
            currentPage = moviesViewModel.currentPage
        )
        MoviesList(
            moviesList = moviesViewModel.moviesPage,
            onItemClicked = { navigateToMovieDetails(it) },
            getMovieId = { moviesViewModel.getFilteredMovieId(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesSearch(
    moviesFieldValue: String, onValueChanged: (String)->Unit, onSearch: ()->Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = moviesFieldValue,
        onValueChange = { onValueChanged(it) },
        label = { Text(text = "Search a Movie or TV series") },
        modifier = Modifier
            .padding(PaddingValues(horizontal = 16.dp, vertical = 16.dp))
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch()
            focusManager.clearFocus()
        }),
        singleLine = true
    )
}

@Preview
@Composable
fun PreviewMoviesSearch(){
    MoviesSearch(moviesFieldValue = "", onValueChanged = {  }, onSearch = {})
}

@Composable
fun CurrentListLabelText(text: String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Yellow,
            textAlign = TextAlign.Center
        )
    }
}
@Preview
@Composable
fun PreviewCurrentCurrentListLabel() {
    CurrentListLabelText(text = "Top Movies")
}

@Composable
fun PageSelector(
    pagesList: List<Int>,
    onPageSelected: (Int) -> Unit,
    currentPage: Int
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(pagesList) { page ->
            TextButton(onClick = { onPageSelected(page) }) {
                Text(
                    text = page.toString(),
                    color = if (page == currentPage) Yellow else Yellow.copy(0.5f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPageSelector(){
    PageSelector(pagesList = listOf(1,2,3,4,5,6,7,8,9,10), onPageSelected = {}, currentPage = 4)
}

@Composable
fun MoviesList(
    moviesList: List<Movies>?,
    onItemClicked: (String) -> Unit,
    getMovieId: (Movies) -> String
) {
    LazyVerticalGrid (
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        if(moviesList!=null) {
            items(moviesList) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = { onItemClicked(getMovieId(movie)) }
                )
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movies, onClick: (String)-> Unit){
    Column(modifier = Modifier.padding(12.dp)){
        Card(
            modifier = Modifier
                .size(width = 160.dp, height = 300.dp)
                .clickable(enabled = true, onClick = { onClick(movie.id) }),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Navy,
                contentColor = Yellow
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                if(movie.image!=null){
                    AsyncImage(
                        model = movie.image.url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxWidth()
                            .height(200.dp),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                MovieCardText(movie = movie)
            }
        }
    }
}

@Preview
@Composable
fun MovieCardPreview(){
    val movie = Movies(
        title = "Game of Thrones", id = "test", numberOfEpisodes = 70, seriesEndYear = 2011,
        seriesStartYear = 2019, titleType = "Series", year = 2011, principals = listOf(),
        image = MovieImage(
            url = "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
            id = "/title/tt0944947/images/rm4204167425",
            width = 1102,
            height = 1500
        ),
    )
    MovieCard(movie = movie, onClick = {})
}

@Composable
fun MovieCardText(movie: Movies){
    Column {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(movie.title!=null) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, top = 4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (movie.seriesStartYear > 0) {
                Text(
                    text = "Series start year: ${movie.seriesStartYear}",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
            if(movie.seriesEndYear > 0 ) {
                Text(
                    text = "Series end year: ${movie.seriesEndYear}",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
            if (movie.seriesEndYear <= 0 && movie.seriesStartYear <= 0 && movie.year > 0) {
                Text(
                    text = "Movie year: ${movie.year}",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}
