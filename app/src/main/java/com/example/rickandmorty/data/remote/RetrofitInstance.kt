package com.example.rickandmorty.data.remote

import com.example.rickandmorty.utils.Constance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Property that lazily initializes
    // the RickApi implementation
    val api: SimpleApi by lazy {

        Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpleApi::class.java)
    }

}