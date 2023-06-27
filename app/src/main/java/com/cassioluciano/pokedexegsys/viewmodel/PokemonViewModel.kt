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

    init {
        viewModelScope.launch {
            loadPokemons()
        }
    }

    private suspend fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            _pokemons.postValue(
                it.map { pokemonResult ->
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
            )
        }
    }
}






//class PokemonViewModel : ViewModel() {
//    var pokemons = MutableLiveData<List<Pokemon?>>()
//
//    init {
//        Thread(
//            Runnable {
//                loadPokemons()
//            }
//        ).start()
//    }
//
//    private fun loadPokemons() {
//        val pokemonsApiResult = PokemonRepository.listPokemons()
//
//        pokemonsApiResult?.results?.let {
//            pokemons.postValue(
//                it.map { pokemonResult ->
//                    val number = pokemonResult.url
//                        .replace("https://pokeapi.co/api/v2/pokemon/", "")
//                        .replace("/", "").toInt()
//
//                    val pokemonApiResult = PokemonRepository.getPokemon(number)
//
//                    pokemonApiResult?.let {
//                        Pokemon(
//                            pokemonApiResult.id,
//                            pokemonApiResult.name,
//                            pokemonApiResult.types.map { type ->
//                                type.type
//                            }
//                        )
//                    }
//                }
//            )
//        }
//    }
//}
