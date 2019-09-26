package br.com.victorchabelman.movierankings.remote

import br.com.victorchabelman.movierankings.model.GenreListContainer
import br.com.victorchabelman.movierankings.model.MovieDetail
import br.com.victorchabelman.movierankings.model.MovieListContainer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("trending/movie/day")
    fun trendingMovies(@Query("api_key") apiKey: String): Call<MovieListContainer>

    @GET("trending/movie/day")
    fun trendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieListContainer>

    @GET("discover/movie?sort_by=popularity.desc")
    fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page : Int
    ) : Call<MovieListContainer>

    @GET("movie/{id}")
    fun movieDetail(
        @Path("id") id : Int,
        @Query("api_key") apiKey: String
    ) : Call<MovieDetail>

    @GET("genre/movie/list")
    fun genres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<GenreListContainer>
}