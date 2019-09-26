package br.com.victorchabelman.movierankings.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.adapters.MovieAdapter
import br.com.victorchabelman.movierankings.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var movieViewModel : MovieViewModel ;

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(this)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.loadGenres()

        movieViewModel.movies.observe(this, Observer {
            movieAdapter.addMovies(it!!)
        })

        movieList.adapter = movieAdapter
        movieList.addOnScrollListener(scrollListener())

        movieViewModel.loadPopularMovies(currentPage)
    }

    private fun scrollListener() : RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE) {
                    currentPage++
                    movieViewModel.loadPopularMovies(currentPage)
                }
            }
        }
    }
}