package br.com.victorchabelman.movierankings.activities

import android.os.Bundle
import android.os.Handler
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.adapters.MovieAdapter
import br.com.victorchabelman.movierankings.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    private var shouldClear = false
    private var isSearching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        movieAdapter = MovieAdapter(this)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.loadGenres()

        movieViewModel.movies.observe(this, Observer {
            if (shouldClear) {
                movieAdapter.updateMovies(it!!)
            } else {
                movieAdapter.addMovies(it!!)
            }
        })

        movieList.adapter = movieAdapter
        movieList.addOnScrollListener(scrollListener())

        movieViewModel.loadPopularMovies()

        sv_movies.setOnQueryTextListener(movieSearchListener())
    }

    private fun scrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    shouldClear = false
                    movieViewModel.updatePage()

                    if (isSearching) {
                        movieViewModel.searchMovies(sv_movies.query.toString())
                    } else{
                        movieViewModel.loadPopularMovies()
                    }
                }
            }
        }
    }

    private fun movieSearchListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            val handler = Handler()
            var runnable = Runnable {
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                handler.removeCallbacks(runnable)
                p0?.let {
                    movieViewModel.clearPage()
                    shouldClear = true

                    if (p0.isBlank()) {
                        isSearching = false
                        movieViewModel.loadPopularMovies()
                    } else {
                        isSearching = true
                        movieViewModel.searchMovies(it)
                    }

                    movieList.smoothScrollToPosition(RecyclerView.SCROLLBAR_POSITION_DEFAULT)
                    return true
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null && p0.isBlank()) return true

                handler.removeCallbacks(runnable)

                runnable = Runnable {
                    onQueryTextSubmit(p0)
                }

                handler.postDelayed(runnable, 3000)
                return false
            }
        }
    }
}