package br.com.victorchabelman.movierankings.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.databinding.ActivityMovieDetailBinding
import br.com.victorchabelman.movierankings.databinding.ViewMovieDetailBinding
import br.com.victorchabelman.movierankings.model.Movie
import br.com.victorchabelman.movierankings.viewmodels.MovieDetailViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val BUNDLE_MOVIE = "BUNDLE_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMovieDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_detail
        )

        val movie : Movie? = intent.getSerializableExtra(BUNDLE_MOVIE) as Movie?
        binding.movie = movie

        val movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel.movieDetail.observe(this, Observer {
            val bind = ViewMovieDetailBinding.inflate(layoutInflater, cl_movie_detail_container, true)
            bind.movie = it
            bind.executePendingBindings()
        })
        movieDetailViewModel.loadMovie(movie!!.id)
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}