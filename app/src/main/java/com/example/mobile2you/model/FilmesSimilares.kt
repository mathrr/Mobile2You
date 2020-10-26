package com.example.mobile2you.model

import com.google.gson.annotations.SerializedName

data class FilmesSimilares (
    @SerializedName("results") val filmesSimilares: List<Filme>
)