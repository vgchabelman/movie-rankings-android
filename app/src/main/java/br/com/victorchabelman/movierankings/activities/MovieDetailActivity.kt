package br.com.victorchabelman.movierankings.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.databinding.ActivityMovieDetailBinding
import br.com.victorchabelman.movierankings.model.Movie

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val BUNDLE_MOVIE = "BUNDLE_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMovieDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_detail
        )

        binding.movie = intent.getSerializableExtra(BUNDLE_MOVIE) as Movie?
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}