package com.cassioluciano.pokedexegsys.api

import com.cassioluciano.pokedexegsys.api.model.PokemonApiResult
import com.cassioluciano.pokedexegsys.api.model.PokemonsApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonService::class.java)

    suspend fun listPokemons(limit: Int, offset: Int): PokemonsApiResult? {
        return withContext(Dispatchers.IO) {
            service.listPokemons(limit, offset).execute().body()
        }
    }

    suspend fun getPokemon(number: Int): PokemonApiResult? {
        return withContext(Dispatchers.IO) {
            service.getPokemon(number).execute().body()
        }
    }
}
