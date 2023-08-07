package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.model.RickMorty
import com.example.rickandmorty.utils.Constance
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    // https://rickandmortyapi.com/api/character
    @GET(Constance.END_POINT)
    suspend fun getAllCharacter() : Response<RickMorty>
}