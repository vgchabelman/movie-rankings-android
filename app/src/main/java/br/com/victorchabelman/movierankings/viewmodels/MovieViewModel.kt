package br.com.victorchabelman.movierankings.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.victorchabelman.movierankings.util.API_KEY
import br.com.victorchabelman.movierankings.model.Movie
import br.com.victorchabelman.movierankings.model.MovieListContainer
import br.com.victorchabelman.movierankings.remote.TmdbService
import br.com.victorchabelman.movierankings.util.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MovieViewModel : ViewModel() {
    val movies = MutableLiveData<List<Movie>>()

    fun loadMovies() {
        val retrofit = RetrofitUtils.createRetrofit()

        val tmdbService = retrofit.create(TmdbService::class.java)

        tmdbService.trendingMovies(API_KEY, "pt-BR").enqueue(object : Callback<MovieListContainer> {
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


}