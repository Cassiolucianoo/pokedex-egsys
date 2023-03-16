package com.cassioluciano.pokedexegsys.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cassioluciano.pokedexegsys.R
import com.cassioluciano.pokedexegsys.domain.Pokemon
import com.cassioluciano.pokedexegsys.viewmodel.PokemonViewModel
import com.cassioluciano.pokedexegsys.viewmodel.PokemonViewModelFactory

class HomeActivity : AppCompatActivity() {

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.rvPokemonList)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}