package com.cassioluciano.pokedexegsys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cassioluciano.pokedexegsys.PokemonResult
import com.cassioluciano.pokedexegsys.R
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.card_pokemon.view.*

class PokemonListAdapter (val pokemon: (Int) -> Unit): RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {
    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    var pokemonList: List<PokemonResult> = emptyList()
    fun setData(list: List<PokemonResult>){
        pokemonList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon, parent,false))
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.itemView.pokemon_code.text = "#${position + 1}"
        holder.itemView.pokemon_title.text = "${pokemon.name.trim().capitalize()}"

    }
    override fun getItemCount(): Int {
        return pokemonList.size
    }

}