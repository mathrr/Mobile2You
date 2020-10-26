package com.example.mobile2you.model

import com.google.gson.annotations.SerializedName

data class Filme (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val titulo: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("popularity") val popularidade: Float,
    @SerializedName("vote_count") val numeroDeVotos : Int,
    @SerializedName("release_date") val dataDeLancamento : String,
    @SerializedName("genre_ids") val genres : List<Int>,
)