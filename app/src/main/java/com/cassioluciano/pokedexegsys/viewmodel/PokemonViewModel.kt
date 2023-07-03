package com.cassioluciano.pokedexegsys.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cassioluciano.pokedexegsys.api.PokemonRepository
import com.cassioluciano.pokedexegsys.domain.Pokemon

import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<List<Pokemon?>>()
    val pokemons: LiveData<List<Pokemon?>> get() = _pokemons

    private var loadedItemCount = 0
    private val loadBatchSize = 8

    init {
        viewModelScope.launch {
            loadPokemons()
        }
    }

    suspend fun loadMorePokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons(loadBatchSize, loadedItemCount)

        pokemonsApiResult?.results?.let {
            val newPokemons = it.map { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            }

            val currentPokemons = _pokemons.value.orEmpty().toMutableList()
            currentPokemons.addAll(newPokemons)
            _pokemons.postValue(currentPokemons)

            loadedItemCount += loadBatchSize
        }
    }

    private  suspend fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons(loadBatchSize, loadedItemCount)
        loadMorePokemons()
    }
}
