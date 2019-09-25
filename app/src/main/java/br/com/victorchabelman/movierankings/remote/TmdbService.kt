package br.com.victorchabelman.movierankings.remote

import br.com.victorchabelman.movierankings.model.MovieListContainer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("trending/movie/day")
    fun trendingMovies(@Query("api_key") apiKey: String): Call<MovieListContainer>

    @GET("trending/movie/day")
    fun trendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieListContainer>
}