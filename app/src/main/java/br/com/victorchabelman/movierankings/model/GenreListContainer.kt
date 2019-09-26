package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName

data class GenreListContainer(@SerializedName("genres") val genres: List<Genre>)