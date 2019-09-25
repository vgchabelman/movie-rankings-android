package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName

data class Movie(@SerializedName("id") val id : Int,
                 @SerializedName("title") val title : String,
                 @SerializedName("original_title") val originalTitle : String,
                 @SerializedName("tagline") val tagline : String,
                 @SerializedName("overview") val overview : String,
                 @SerializedName("poster_path") val posterPath : String,
                 @SerializedName("backdrop_path") val backdropPath : String,
                 @SerializedName("release_date") val releaseDate : String,
                 @SerializedName("popularity") val popularity : Double,
                 @SerializedName("vote_average") val averageScore : Double,
                 @SerializedName("genres") val genreList: List<Genre>
                 ) {

    fun getMovieGenres() : String {
        var genres = "("
        genreList.forEach {
            genres = genres.plus('/' + it.name)
        }
        return genres.plus(')')
    }

    fun getBackgroundImage() : String {
        return "https://image.tmdb.org/t/p/w500$backdropPath"
    }

    fun getPosterImage() : String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }
}