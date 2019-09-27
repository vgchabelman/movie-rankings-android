package br.com.victorchabelman.movierankings.model

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val country: String
) {
    fun getProductionLogo() : String {
        return "https://image.tmdb.org/t/p/w500$logoPath"
    }
}