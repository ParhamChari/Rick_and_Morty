package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.RickMorty
import com.example.rickandmorty.data.remote.RetrofitInstance
import retrofit2.Response

class CharacterRepository {

    suspend fun getAllCharacter() : Response<RickMorty> = RetrofitInstance.api.getAllCharacter()
}