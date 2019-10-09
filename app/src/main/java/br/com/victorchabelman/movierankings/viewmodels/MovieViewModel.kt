package br.com.victorchabelman.movierankings.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.victorchabelman.movierankings.di.scope.ActivityScope
import br.com.victorchabelman.movierankings.model.Genre
import br.com.victorchabelman.movierankings.model.GenreListContainer
import br.com.victorchabelman.movierankings.model.Movie
import br.com.victorchabelman.movierankings.model.MovieListContainer
import br.com.victorchabelman.movierankings.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class MovieViewModel
@Inject
constructor(var movieRepository: MovieRepository) : ViewModel() {
    val movies = MutableLiveData<List<Movie>>()
    val genres = ArrayList<Genre>()
    lateinit var noConnectionListener: INoConnectionWarn

    private var currentPage = 1

    fun loadTrendingMovies() {
        movieRepository.getTrendingMovies().enqueue(object : Callback<MovieListContainer> {
            override fun onFailure(call: Call<MovieListContainer>, t: Throwable) {
                Log.e("VGC", t.message)
            }

            override fun onResponse(
                call: Call<MovieListContainer>,
                response: Response<MovieListContainer>
            ) {
                if (response.code() == 200) {
                    val movieListContainer = response.body()!!

                    movies.postValue(movieListContainer.moviesList)
                }
            }
        })
    }

    fun loadGenres() {
        movieRepository.getGenres().enqueue(object : Callback<GenreListContainer> {
            override fun onFailure(call: Call<GenreListContainer>, t: Throwable) {
                Log.e("VGC", t.message)
            }

            override fun onResponse(
                call: Call<GenreListContainer>,
                response: Response<GenreListContainer>
            ) {
                if (response.code() == 200) {
                    genres.clear()
                    response.body()?.genres?.let { genres.addAll(it) }
                }
            }
        })
    }

    fun updatePage() {
        currentPage++
    }

    fun clearPage() {
        currentPage = 1
    }

    fun loadPopularMovies(hasConnection: Boolean) {
        if (!hasConnection) {
            noConnectionListener.onNoConnectionDetected()
            return
        }

        movieRepository.getPopularMovies(currentPage)
            .enqueue(object : Callback<MovieListContainer> {
                override fun onFailure(call: Call<MovieListContainer>, t: Throwable) {
                    Log.e("VGC", t.message)
                }

                override fun onResponse(
                    call: Call<MovieListContainer>,
                    response: Response<MovieListContainer>
                ) {
                    if (response.code() == 200) {
                        val movieListContainer = response.body()!!

                        movieListContainer.moviesList.forEach { movie ->
                            movie.setupGenreText(genres = genres.map { it.id to it }.toMap())
                        }
                        movies.postValue(movieListContainer.moviesList)
                    }
                }
            })
    }

    fun searchMovies(query: String, hasConnection: Boolean) {
        if (!hasConnection) {
            noConnectionListener.onNoConnectionDetected()
            return
        }

        movieRepository.getMovies(page = currentPage, query = query)
            .enqueue(object : Callback<MovieListContainer> {
                override fun onFailure(call: Call<MovieListContainer>, t: Throwable) {
                    Log.e("VGC", t.message)
                }

                override fun onResponse(
                    call: Call<MovieListContainer>,
                    response: Response<MovieListContainer>
                ) {
                    if (response.code() == 200) {
                        val movieListContainer = response.body()!!

                        movieListContainer.moviesList.forEach { movie ->
                            movie.setupGenreText(genres = genres.map { it.id to it }.toMap())
                        }
                        movies.postValue(movieListContainer.moviesList)
                    }
                }
            })
    }

    interface INoConnectionWarn {
        fun onNoConnectionDetected()
    }
}