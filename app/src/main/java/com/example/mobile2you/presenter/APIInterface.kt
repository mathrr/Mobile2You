package com.example.mobile2you.presenter

import com.example.mobile2you.model.Filme
import com.example.mobile2you.model.FilmesSimilares
import com.example.mobile2you.model.Genres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("movie/{movie_id}")
    fun getFilme(
        @Path("movie_id") movie_id : String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<Filme>

    @GET("movie/{movie_id}/similar")
    fun getFilmesSimilares(
        @Path("movie_id") movie_id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<FilmesSimilares>

    @GET("genre/movie/list")
    fun getGeneros(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<List<Genres>>
}