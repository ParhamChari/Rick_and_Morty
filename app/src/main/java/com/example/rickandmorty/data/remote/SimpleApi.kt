package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.RickMorty
import com.example.rickandmorty.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    // https://rickandmortyapi.com/api/character
    @GET(Constants.END_POINT)
    suspend fun getAllCharacter() : Response<RickMorty>
}