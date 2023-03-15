package com.cassioluciano.pokedexegsys

import com.cassioluciano.pokedexegsys.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface PokemonService {
    @GET("pokemon")
    fun getPokemonsList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonApiResponse>
}