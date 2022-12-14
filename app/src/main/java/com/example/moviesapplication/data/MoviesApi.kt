package com.example.moviesapplication.data

import com.example.moviesapplication.model.MoviesApiResponse
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

    companion object{
        private const val BASE_URL="https://online-movie-database.p.rapidapi.com/"
        private const val API_KEY = "b30e63bc29msh8e8ee2e35e96f94p1be304jsnd7f67794f8e0"
        private const val API_HOST = "online-movie-database.p.rapidapi.com"
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