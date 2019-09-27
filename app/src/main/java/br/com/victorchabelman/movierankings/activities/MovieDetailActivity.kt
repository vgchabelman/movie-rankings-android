package br.com.victorchabelman.movierankings.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.databinding.ActivityMovieDetailBinding
import br.com.victorchabelman.movierankings.databinding.ItemProductionCompanyBinding
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

        val movie: Movie? = intent.getSerializableExtra(BUNDLE_MOVIE) as Movie?
        binding.movie = movie

        setSupportActionBar(tb_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = ""

        val movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel.movieDetail.observe(this, Observer {
            val bind =
                ViewMovieDetailBinding.inflate(layoutInflater, cl_movie_detail_container, true)
            bind.movie = it
            it.companyList.forEach { productionCompany ->
                val companyBind =
                    ItemProductionCompanyBinding.inflate(layoutInflater, bind.glCompanies, true)
                companyBind.pc = productionCompany
                companyBind.executePendingBindings()
            }
            bind.executePendingBindings()
        })
        movieDetailViewModel.loadMovie(movie!!.id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    fun onClickUrl(view: View) {
        val url = (view as TextView).text.toString()

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}