package br.com.victorchabelman.movierankings.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.victorchabelman.movierankings.model.MovieDetail
import br.com.victorchabelman.movierankings.remote.TmdbService
import br.com.victorchabelman.movierankings.util.API_KEY
import br.com.victorchabelman.movierankings.util.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {
    var movieDetail = MutableLiveData<MovieDetail>()

    fun loadMovie(id : Int) {
        val tmdbService = RetrofitUtils.retrofitInstance.create(TmdbService::class.java)

        tmdbService.movieDetail(id, API_KEY).enqueue(object : Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e("VGC", t.message)
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.code() == 200) {
                    movieDetail.postValue(response.body())
                }
            }
        })
    }
}