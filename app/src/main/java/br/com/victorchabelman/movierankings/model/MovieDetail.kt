package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat

data class MovieDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("budget") val budget: Long,
    @SerializedName("genres") val genreList: List<Genre>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val companyList: List<ProductionCompany>,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("tagline") val tagline : String,
    @SerializedName("vote_average") val averageScore: Double,
    @SerializedName("vote_count") val voteCount: Int) {

    fun getBudgetFormatted() : String{
        return NumberFormat.getCurrencyInstance().format(budget)
    }

    fun getGenreTexts() : String {
        var genreTexts = ""
        genreList.forEach {
            genreTexts = genreTexts.plus(it.name + '/')
        }
        return genreTexts.substringBeforeLast('/').replace("null", "")
    }
}