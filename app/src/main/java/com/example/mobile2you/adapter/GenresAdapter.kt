package com.example.mobile2you.adapter

import com.example.mobile2you.model.Genres
import com.example.mobile2you.retrofit.FilmeRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun retornaGeneros(ids: List<Int>): String {
    var generosString = ""
    val call: Call<List<Genres>> =
        FilmeRetrofit().apiInterface.getGeneros("5dc5091fc0142ab27175fd181084427b")
    call.enqueue(object : Callback<List<Genres>> {

        override fun onFailure(call: Call<List<Genres>>?, t: Throwable?) {
            generosString = "missing"
        }

        override fun onResponse(call: Call<List<Genres>>?, response: Response<List<Genres>>?) {
            val generosResponse: List<Genres> = response?.body()!!
            val generosMap = mutableMapOf<Int, String>()
            generosResponse.forEach {
                generosMap[it.id] = it.name
            }
            generosString = generosMap[ids[0]].toString() + ", " + generosMap[ids[1]].toString()
        }

    })
    return generosString
}