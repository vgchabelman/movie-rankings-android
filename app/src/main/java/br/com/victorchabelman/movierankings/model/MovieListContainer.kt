package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName

data class MovieListContainer(@SerializedName("page") val page : Int,
                              @SerializedName("results") val moviesList: List<Movie>,
                              @SerializedName("total_pages") val numPages : Int,
                              @SerializedName("total_results") val totalResults : Int)