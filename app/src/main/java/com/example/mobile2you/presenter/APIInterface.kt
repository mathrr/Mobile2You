package com.example.mobile2you.presenter

import com.example.mobile2you.model.Filme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("{movie_id}")
    fun getFilme(
        @Path("movie_id") movie_id : String,
        @Query("api_key") apiKey: String = "5dc5091fc0142ab27175fd181084427b",
        @Query("language") language: String = "en-US"
    ): Call<Filme>

}