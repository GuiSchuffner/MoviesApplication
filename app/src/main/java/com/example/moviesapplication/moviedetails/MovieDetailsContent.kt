package com.example.moviesapplication.moviedetails

import android.provider.MediaStore.Audio.Genres
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapplication.R
import com.example.moviesapplication.model.*
import com.example.moviesapplication.ui.theme.Yellow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailsContent(
    id: String,
    moviesViewModel: MovieDetailsViewModel = koinViewModel(parameters = { parametersOf(id) })
) {
    Column {
        if(moviesViewModel.movieOverview != null) {
            MovieOverviewContent(movieOverview = moviesViewModel.movieOverview!!)
        }
    }
}

@Preview
@Composable
fun MovieOverviewContentPreview() {
    val movieOverview = getMovieOverviewTestObject()
    MovieOverviewContent(movieOverview = movieOverview)
}

@Composable
fun MovieOverviewContent(movieOverview: MovieOverview) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        MovieOverviewImage(imageUrl = movieOverview.title.image?.url)
        MovieDetailsCard(
            movieOverview.title,
            movieOverview.rating,
            movieOverview.genres,
            movieOverview.releaseDate
        )
        Divider(Modifier.fillMaxWidth(), 1.dp, Yellow)
        PlotOutlineCard(plotText = movieOverview.plotOutline.text)
        Divider(Modifier.fillMaxWidth(), 1.dp, Yellow)
        PlotSummaryCard(
            plotSummary = movieOverview.plotSummary.text,
            author = stringResource( R.string.summary_author, movieOverview.plotSummary.author ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsCard(
    movie: Movies, rating: MovieRatings?, genres: List<String>, releaseDate: String
){
    var showMovieDetails by remember { mutableStateOf(false) }
    Card(
        onClick = { showMovieDetails = !showMovieDetails },
        modifier = Modifier.padding(top = 16.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Yellow
        )
    ) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            CardTitle(cardTitle = "Show details", showDetails = showMovieDetails)
            if(showMovieDetails) {
                MovieDetailsText(movie, rating, genres, releaseDate)
            }
        }
    }
}

@Composable
fun MovieDetailsText(
    movie: Movies, rating: MovieRatings?, genres: List<String>, releaseDate: String
) {
    if(movie.title!=null) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
    }
    Text(
        text = "Release date: $releaseDate",
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Justify
    )
    if(movie.seriesEndYear > 0 ) {
        Text(
            text = "Series end year: ${movie.seriesEndYear}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
    if (rating != null && rating.canRate) {
        Text(
            text = "Rating: ${rating.rating}",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
    if(genres.isNotEmpty()) {
        var genresString = ""
        for(genre in genres) {
            if(genresString.isEmpty()) {
                genresString=genre
            }
            else {
                genresString+=", $genre"
            }
        }
        Text(
            text = "Genres: $genresString",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun MovieOverviewImage(imageUrl: String?) {
    if(imageUrl!=null)
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlotOutlineCard(plotText: String) {
    var showPlotOutline by remember { mutableStateOf(false) }
    Card(
        onClick = { showPlotOutline = !showPlotOutline },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Yellow
        )
    ) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            CardTitle(
                cardTitle = stringResource(id = R.string.plot_outline),
                showDetails = showPlotOutline
            )
            if(showPlotOutline) {
                Text(
                    text = plotText,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview
@Composable
fun PlotOutlineCardPreview(){
    PlotOutlineCard(plotText = "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlotSummaryCard(plotSummary: String, author: String) {
    var showPlotSummary by remember { mutableStateOf(false) }
    Card(
        onClick = { showPlotSummary = !showPlotSummary },
        modifier = Modifier.padding(bottom = 16.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Yellow
        )
    ) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            CardTitle(
                cardTitle = stringResource(id = R.string.plot_summary),
                showDetails = showPlotSummary
            )
            if(showPlotSummary) {
                Text(
                    text = plotSummary,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = author,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun CardTitle(cardTitle: String, showDetails: Boolean) {
    val imageId =
        if(showDetails) R.drawable.baseline_arrow_drop_up_24
        else R.drawable.baseline_arrow_drop_down_24
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = cardTitle,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            alignment = Alignment.TopEnd,
            colorFilter = ColorFilter.tint(Yellow)
        )
    }
}

fun getMovieOverviewTestObject(): MovieOverview {
    return MovieOverview(
        id = "/title/tt0944947/",
        title = Movies(
            id = "/title/tt0944947/",
            image = MovieImage(
                url = "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_.jpg",
                id = "/title/tt0944947/images/rm4204167425",
                width = 1102,
                height = 1500
            ),
            year = 2011,
            title = "Game of Thrones",
            principals = listOf()
        ),
        plotOutline = PlotOutline(
            id="/title/tt0944947/plot/po2596634",
            text = "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia."
        ),
        plotSummary = PlotSummary(
            "/title/tt0944947/plot/ps2733843",
            "Sam Gray",
            "In the mythical continent of Westeros, several powerful families fight for control of the Seven Kingdoms. As conflict erupts in the kingdoms of men, an ancient enemy rises once again to threaten them all. Meanwhile, the last heirs of a recently usurped dynasty plot to take back their homeland from across the Narrow Sea."
        ),
        genres = listOf("Action", "Drama", "Fantasy"),
        releaseDate = "2011-04-17",
        rating = MovieRatings(
            canRate = true,
            rating = 9.8
        )
    )
}
