package br.com.victorchabelman.movierankings.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.adapters.MovieAdapter
import br.com.victorchabelman.movierankings.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter()

        val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieViewModel.movies.observe(this, Observer {
            movieAdapter.updateMovies(it!!)
        })

        movieList.adapter = movieAdapter

        movieViewModel.loadMovies()
    }
}