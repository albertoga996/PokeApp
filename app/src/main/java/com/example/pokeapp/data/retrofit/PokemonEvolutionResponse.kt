package com.example.pokeapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionResponse(
    @SerializedName("chain")
    var chain: Chain
)

data class Chain(
    @SerializedName("evolves_to")
    var evolvesTo: List<Chain>,

    @SerializedName("species")
    var species: Species
)

data class Species(
    @SerializedName("name")
    var name: String
)

