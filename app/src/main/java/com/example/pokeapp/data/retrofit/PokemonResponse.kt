package com.example.pokeapp.data.retrofit

import com.example.pokeapp.db.entities.Pokemon
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results")
    @Expose
    val data: List<Pokemon>
)
