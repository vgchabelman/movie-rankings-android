package br.com.victorchabelman.movierankings.repository

import br.com.victorchabelman.movierankings.model.GenreListContainer
import br.com.victorchabelman.movierankings.model.MovieDetail
import br.com.victorchabelman.movierankings.model.MovieListContainer
import retrofit2.Call

interface MovieRepository {
    fun getTrendingMovies() : Call<MovieListContainer>

    fun getPopularMovies(page : Int) : Call<MovieListContainer>

    fun getMovieDetail(id : Int) : Call<MovieDetail>

    fun getMovies(query : String, page : Int): Call<MovieListContainer>

    fun getGenres() : Call<GenreListContainer>
}