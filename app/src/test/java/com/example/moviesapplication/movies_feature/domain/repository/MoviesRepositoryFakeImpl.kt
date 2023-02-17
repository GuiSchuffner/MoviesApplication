package com.example.moviesapplication.movies_feature.domain.repository

import com.example.moviesapplication.movies_feature.data.repository.MoviesRepository
import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.domain.model.MoviesApiResponse
import com.google.gson.Gson

class MoviesRepositoryFakeImpl: MoviesRepository {

    //the first ten movies in the list of most popular movies
    private val topMovies: List<Movies> =
        Gson().fromJson(tenMostPopularMoviesJson, Array<Movies>::class.java).asList()

    override suspend fun searchTitle(title: String): MoviesApiResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getMostPopularMoviesId(): List<String> {
        return topMoviesList
    }

    override suspend fun getMovieDetails(id: String): Movies {
        return topMovies.find { movie -> movie.id.contains(id) }
            ?: throw Exception("movie not found")
    }

    override suspend fun getMovieOverview(title: String): MovieOverview {
        TODO("Not yet implemented")
    }
}

private val topMoviesList: List<String> = listOf(
    "/title/tt9114286/",
    "/title/tt10640346/",
    "/title/tt15679400/",
    "/title/tt14826022/",
    "/title/tt1630029/",
    "/title/tt5884796/",
    "/title/tt9764362/",
    "/title/tt11813216/",
    "/title/tt16419074/",
    "/title/tt6710474/",
    "/title/tt5433140/",
    "/title/tt3915174/",
    "/title/tt8760708/",
    "/title/tt10954600/",
    "/title/tt16280138/",
    "/title/tt13833688/",
    "/title/tt9686790/",
    "/title/tt12823454/",
    "/title/tt14444726/",
    "/title/tt7322224/",
    "/title/tt18079362/",
    "/title/tt14208870/",
    "/title/tt12593682/",
    "/title/tt11564570/",
    "/title/tt13051810/",
    "/title/tt2353868/",
    "/title/tt10365998/",
    "/title/tt1016150/",
    "/title/tt1745960/",
    "/title/tt7405458/",
    "/title/tt17663992/",
)

//the first 10 movies in the list of most popular movies
private const val tenMostPopularMoviesJson: String = "[{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt9114286/\",\"image\":{\"height\":2500,\"id\":\"/title/tt9114286/images/rm2876833793\",\"url\":\"https://m.media-amazon.com/images/M/MV5BNTM4NjIxNmEtYWE5NS00NDczLTkyNWQtYThhNmQyZGQzMjM0XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg\",\"width\":1688},\"runningTimeInMinutes\":161,\"title\":\"BlackPanther:WakandaForever\",\"titleType\":\"movie\",\"year\":2022},{\"@type\":\"imdb.api.title.title\",\"disambiguation\":\"I\",\"id\":\"/title/tt10640346/\",\"image\":{\"height\":3000,\"id\":\"/title/tt10640346/images/rm865155841\",\"url\":\"https://m.media-amazon.com/images/M/MV5BNjlkYjc4NGMtZjc3MS00NjQ3LTk4MmUtMTkwZGZjODE1ZDVlXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg\",\"width\":2025},\"runningTimeInMinutes\":189,\"title\":\"Babylon\",\"titleType\":\"movie\",\"year\":2022},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt15679400/\",\"image\":{\"height\":5000,\"id\":\"/title/tt15679400/images/rm2274573057\",\"url\":\"https://m.media-amazon.com/images/M/MV5BZTc4MjU0MjMtYTEwNy00YjNlLTk2MGYtMjNlNzFjMmY4MjQ0XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg\",\"width\":3158},\"runningTimeInMinutes\":100,\"title\":\"KnockattheCabin\",\"titleType\":\"movie\",\"year\":2023},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt14826022/\",\"image\":{\"height\":1566,\"id\":\"/title/tt14826022/images/rm1421947393\",\"url\":\"https://m.media-amazon.com/images/M/MV5BNGM2NTQ2MGUtYmVjZS00YTNjLTkxMjUtOWQ2ZjRiMDQyNmVlXkEyXkFqcGdeQXVyMTE5MTg5NDIw._V1_.jpg\",\"width\":1044},\"runningTimeInMinutes\":117,\"title\":\"YouPeople\",\"titleType\":\"movie\",\"year\":2023},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt1630029/\",\"image\":{\"height\":1289,\"id\":\"/title/tt1630029/images/rm92486145\",\"url\":\"https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1_.jpg\",\"width\":900},\"runningTimeInMinutes\":192,\"title\":\"Avatar:TheWayofWater\",\"titleType\":\"movie\",\"year\":2022},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt5884796/\",\"image\":{\"height\":1500,\"id\":\"/title/tt5884796/images/rm1003029505\",\"url\":\"https://m.media-amazon.com/images/M/MV5BZDc4MzVkNzYtZTRiZC00ODYwLWJjZmMtMDIyNjQ1M2M1OGM2XkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_.jpg\",\"width\":1013},\"runningTimeInMinutes\":107,\"title\":\"Plane\",\"titleType\":\"movie\",\"year\":2023},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt9764362/\",\"image\":{\"height\":2000,\"id\":\"/title/tt9764362/images/rm1001914881\",\"url\":\"https://m.media-amazon.com/images/M/MV5BMzdjNjI5MmYtODhiNS00NTcyLWEzZmUtYzVmODM5YzExNDE3XkEyXkFqcGdeQXVyMTAyMjQ3NzQ1._V1_.jpg\",\"width\":1334},\"runningTimeInMinutes\":107,\"title\":\"TheMenu\",\"titleType\":\"movie\",\"year\":2022},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt11813216/\",\"image\":{\"height\":5625,\"id\":\"/title/tt11813216/images/rm1356926465\",\"url\":\"https://m.media-amazon.com/images/M/MV5BM2NlZDI0ZDktNTg5OS00ZjQ1LWI4MDEtN2I0MDE5NWRiNzA4XkEyXkFqcGdeQXVyMTY5Nzc4MDY@._V1_.jpg\",\"width\":3695},\"runningTimeInMinutes\":114,\"title\":\"TheBansheesofInisherin\",\"titleType\":\"movie\",\"year\":2022},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt16419074/\",\"image\":{\"height\":12005,\"id\":\"/title/tt16419074/images/rm4090179841\",\"url\":\"https://m.media-amazon.com/images/M/MV5BZGZmMDJkMzMtOGFmOS00ODEzLWIzZWUtMjNkYzFkMDQxYjkwXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_.jpg\",\"width\":8114},\"title\":\"Air\",\"titleType\":\"movie\",\"year\":2023},{\"@type\":\"imdb.api.title.title\",\"id\":\"/title/tt6710474/\",\"image\":{\"height\":12599,\"id\":\"/title/tt6710474/images/rm316021249\",\"url\":\"https://m.media-amazon.com/images/M/MV5BYTdiOTIyZTQtNmQ1OS00NjZlLWIyMTgtYzk5Y2M3ZDVmMDk1XkEyXkFqcGdeQXVyMTAzMDg4NzU0._V1_.jpg\",\"width\":8699},\"runningTimeInMinutes\":139,\"title\":\"EverythingEverywhereAllatOnce\",\"titleType\":\"movie\",\"year\":2022}]"
