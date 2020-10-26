package com.example.mobile2you.model

import android.widget.Toast
import com.example.mobile2you.R
import com.example.mobile2you.retrofit.FilmeRetrofit
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Genres (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
){
    fun displayGenero() {
    val call: Call<List<Genres>> = FilmeRetrofit().apiInterface.getGeneros("5dc5091fc0142ab27175fd181084427b")
    call.enqueue(object : Callback<List<Genres>> {
        override fun onFailure(call: Call<List<Genres>>?, t: Throwable?) {

        }

        override fun onResponse(call: Call<List<Genres>>?, response: Response<List<Genres>>?) {
            var generos : List<Genres> = response?.body()!!

        }

    })}
}

