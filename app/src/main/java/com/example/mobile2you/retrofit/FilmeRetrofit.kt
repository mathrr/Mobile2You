package com.example.mobile2you.retrofit

import com.example.mobile2you.presenter.APIInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class FilmeRetrofit(
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
) {
    var apiInterface: APIInterface = retrofit.create(APIInterface :: class.java)

}