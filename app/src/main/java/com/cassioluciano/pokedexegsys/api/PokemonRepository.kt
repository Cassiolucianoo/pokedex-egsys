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

    suspend fun listPokemons(limit: Int = 8): PokemonsApiResult? {
        return withContext(Dispatchers.IO) {
            service.listPokemons(limit, 0).execute().body()
        }
    }

    suspend fun getPokemon(number: Int): PokemonApiResult? {
        return withContext(Dispatchers.IO) {
            service.getPokemon(number).execute().body()
        }
    }
}



//object PokemonRepository {
//    private val service: PokemonService
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://pokeapi.co/api/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        service = retrofit.create(PokemonService::class.java)
//    }
//
//    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
//        val call = service.listPokemons(limit)
//
//        return call.execute().body()
//    }
//
//    fun getPokemon(number: Int): PokemonApiResult? {
//        val call = service.getPokemon(number)
//
//        return call.execute().body()
//    }
//}
