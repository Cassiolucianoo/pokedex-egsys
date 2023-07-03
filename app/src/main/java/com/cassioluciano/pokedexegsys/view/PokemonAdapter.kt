package com.cassioluciano.pokedexegsys.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cassioluciano.pokedexegsys.R
import com.cassioluciano.pokedexegsys.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.pokemon_image)
            val tvNumber = findViewById<TextView>(R.id.pokemon_code)
            val tvName = findViewById<TextView>(R.id.pokemon_title)


            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)

                tvNumber.text = "Nº ${item.formattedNumber}"
                tvName.text = item.formattedName

            }
        }
    }
}