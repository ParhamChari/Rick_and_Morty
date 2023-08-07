package com.example.rickandmorty.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.ui.viewmodel.CharacterViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val repository: CharacterRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(repository) as T
    }
}