package com.example.rickandmorty.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class RickAndMortyViewHolder(private val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindViews(model : Characters) {
        binding.showName.text = model.name
        Picasso.get().load(model.image).into(binding.characterImage)
    }
}