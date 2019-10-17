package br.com.victorchabelman.movierankings.repository

import br.com.victorchabelman.movierankings.model.GenreListContainer
import br.com.victorchabelman.movierankings.model.MovieDetail
import br.com.victorchabelman.movierankings.model.MovieListContainer
import br.com.victorchabelman.movierankings.remote.TmdbService
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataRepository
@Inject
constructor(var movieService: TmdbService) : MovieRepository {
    companion object {
        private const val LANGUAGE = "pt-BR"
        private const val API_KEY = "b45125153250b6f8a207a680811158ed"
    }

    override fun getTrendingMovies(): Call<MovieListContainer> {
        return movieService.trendingMovies(API_KEY, LANGUAGE)
    }

    override fun getPopularMovies(page: Int): Call<MovieListContainer> {
        return movieService.popularMovies(API_KEY, LANGUAGE, page)
    }

    override fun getMovieDetail(id: Int): Call<MovieDetail> {
        return movieService.movieDetail(id, API_KEY, LANGUAGE)
    }

    override fun getMovies(query: String, page: Int): Call<MovieListContainer> {
        return movieService.searchMovies(API_KEY, LANGUAGE, page, query)
    }

    override fun getGenres(): Call<GenreListContainer> {
        return movieService.genres(API_KEY, LANGUAGE)
    }
}