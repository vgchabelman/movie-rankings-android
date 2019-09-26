package br.com.victorchabelman.movierankings.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.victorchabelman.movierankings.R
import br.com.victorchabelman.movierankings.activities.MovieDetailActivity
import br.com.victorchabelman.movierankings.databinding.ItemMovieBinding
import br.com.victorchabelman.movierankings.model.Movie

class MovieAdapter(val activity: Activity) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var movieList = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ViewHolder(movieBinding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.BUNDLE_MOVIE, movie)

            val posterView = holder.itemView.findViewById<View>(R.id.iv_movie_poster)
            val options = ActivityOptions.makeSceneTransitionAnimation(activity,
                posterView, "poster")
            activity.startActivity(intent, options.toBundle())
        }
    }

    fun updateMovies(movies : List<Movie>) {
        movieList.clear()
        addMovies(movies)
    }

    fun addMovies(movies : List<Movie>) {
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}