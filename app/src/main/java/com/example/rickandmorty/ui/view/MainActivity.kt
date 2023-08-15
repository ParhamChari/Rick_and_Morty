package com.example.rickandmorty.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.adapter.RickAndMortyAdapter
import com.example.rickandmorty.ui.viewmodel.CharacterViewModel
import com.example.rickandmorty.ui.viewmodel.factory.ViewModelFactory
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterViewModel
    private val characterAdapter by lazy { RickAndMortyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showViews()

    }

    private fun bindViews() {
        val repository = CharacterRepository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacterViewModel::class.java]

        viewModel.getAllCharacter()
        viewModel.characterResponse.observe(this) { response ->

            if (response.isSuccessful)
                response.body()!!.results.let { characterAdapter.setData(it) }
            else Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()

        }

        // initializing recycler view
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    private fun showViews() {
        if (checkForInternet(this)) {
            bindViews()
        }
        else {

            AlertDialog.Builder(this).apply {
                setTitle("Internet Error")
                setMessage("Please check your internet connection")

                setPositiveButton("RETRY") { _, _ ->
                    showViews()
                }

                setNegativeButton("EXIT") { _, _ ->
                    MainActivity().finish()
                    moveTaskToBack(true)
                    exitProcess(-1)
                }

                setCancelable(false)
            }.create().show()
        }

    }

}