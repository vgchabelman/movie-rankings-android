package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(@SerializedName("id") val id : Int,
                 @SerializedName("title") val title : String,
                 @SerializedName("original_title") val originalTitle : String,
                 @SerializedName("overview") val overview : String,
                 @SerializedName("poster_path") val posterPath : String,
                 @SerializedName("backdrop_path") val backdropPath : String,
                 @SerializedName("release_date") val releaseDate : String,
                 @SerializedName("popularity") val popularity : Double,
                 @SerializedName("vote_average") val averageScore : Double,
                 @SerializedName("vote_count") val voteCount : Int,
                 @SerializedName("genre_ids") val genreIds : List<Int>?
                 ) : Serializable {

    var genreTexts : String = ""

    fun getBackgroundImage() : String {
        return "https://image.tmdb.org/t/p/w500$backdropPath"
    }

    fun getPosterImage() : String {
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }

    fun setupGenreText(genres : Map<Int, Genre>) {
        genreIds?.let {
            genreIds.forEach {
                genreTexts = genreTexts.plus(genres[it]?.name + '/')
            }
            try {
                genreTexts = genreTexts.substringBeforeLast('/').replace("null", "")
            } catch (e: Exception) {
            }
        }
    }
}