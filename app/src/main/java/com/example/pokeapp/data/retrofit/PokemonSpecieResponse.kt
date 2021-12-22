package com.example.pokeapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class PokemonSpecieResponse(
    @SerializedName("base_happiness")
    var baseHappiness: Int,

    @SerializedName("capture_rate")
    var captureRate: Int,

    @SerializedName("egg_groups")
    var eggGroups: List<EggGroups>,

    @SerializedName("evolution_chain")
    var evolutionChain: EvolutionChain
)

data class EggGroups(
    @SerializedName("name")
    var name: String,

    @SerializedName("url")
    var url: String
)

data class EvolutionChain(
    @SerializedName("url")
    var url: String
)
