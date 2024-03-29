package com.example.moviesapplication.movies_feature.data.data_source

import com.example.moviesapplication.movies_feature.domain.model.MovieOverview
import com.example.moviesapplication.movies_feature.domain.model.Movies
import com.example.moviesapplication.movies_feature.domain.model.MoviesApiResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("title/find")
    suspend fun findTitle(
        @Query("q") q: String
    ): Response<MoviesApiResponse>

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("title/get-details")
    suspend fun getDetails(
        @Query("tconst") id: String
    ): Movies

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("title/get-most-popular-movies")
    suspend fun getMostPopularMovies() : List<String>

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "X-RapidAPI-Host: $API_HOST"
    )
    @GET("title/get-overview-details")
    suspend fun getOverviewDetails(
        @Query("tconst") id: String
    ) : MovieOverview


    companion object{
        private const val BASE_URL="https://online-movie-database.p.rapidapi.com/"
        private const val API_KEY = "b30e63bc29msh8e8ee2e35e96f94p1be304jsnd7f67794f8e0"
        private const val API_HOST = "online-movie-database.p.rapidapi.com"
        val MOVIES_GENRE_LIST = listOf(
            "action","adventure","animation","biography","comedy","crime","documentary","drama",
            "family","fantasy","film-noir","game-show","history","horror","music","musical",
            "mystery","news","reality-tv","romance","sci-fi","short","sport","talk-show",
            "thriller","war","western"
        )

        fun create(): MoviesApi {
            val client = OkHttpClient().newBuilder()
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}