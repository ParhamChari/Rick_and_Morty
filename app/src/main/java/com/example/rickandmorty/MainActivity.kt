package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.adapter.RickAndMortyAdapter
import com.example.rickandmorty.ui.viewmodel.CharacterViewModel
import com.example.rickandmorty.ui.viewmodel.factory.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel
    private val characterAdapter by lazy { RickAndMortyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        // initializing recycler view
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }

        val repository = CharacterRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacterViewModel::class.java]

        viewModel.getAllCharacter()
        viewModel.characterResponse.observe(this) { response ->

            if (response.isSuccessful)
                response.body()!!.results.let { characterAdapter.setData(it) }
            else Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()

        }
    }


}