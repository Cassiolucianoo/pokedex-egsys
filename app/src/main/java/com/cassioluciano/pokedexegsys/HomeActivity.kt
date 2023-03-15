package com.cassioluciano.pokedexegsys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cassioluciano.pokedexegsys.R
import com.cassioluciano.pokedexegsys.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initUI()
    }private fun initUI() {

        rvPokemonList.layoutManager = LinearLayoutManager(this)
        rvPokemonList.adapter = PokemonListAdapter{
        }
        setViewModel()

    }
    private fun setViewModel() {
        viewModel.getPokemonList()
        viewModel.pokemonList.observe(this, Observer {
                list -> (rvPokemonList.adapter as PokemonListAdapter).setData(list)
        })
    }
}