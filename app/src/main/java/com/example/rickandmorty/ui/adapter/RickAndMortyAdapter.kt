package com.example.rickandmorty.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.databinding.CharacterItemBinding

class RickAndMortyAdapter : RecyclerView.Adapter<RickAndMortyViewHolder>() {
    // List of characters to display
    var characterList = ArrayList<Characters>()

    // Set the data for the adapter and
    // notify the RecyclerView of the change

    @SuppressLint("NotifyDataSetChanged")
    fun setData(model : List<Characters>){
        this.characterList = model as ArrayList<Characters>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        return RickAndMortyViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        holder.bindViews(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}