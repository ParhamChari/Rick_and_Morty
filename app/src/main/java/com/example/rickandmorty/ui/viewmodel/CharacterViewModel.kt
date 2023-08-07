package com.example.rickandmorty.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.RickMorty
import com.example.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    val characterResponse: MutableLiveData<Response<RickMorty>> = MutableLiveData()
    fun getAllCharacter() {
        viewModelScope.launch {
            val response: Response<RickMorty> = repository.getAllCharacter()
            characterResponse.value = response
        }
    }

}