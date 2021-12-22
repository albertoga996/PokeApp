package com.example.pokeapp.data.retrofit

import com.google.gson.annotations.SerializedName

data class PokemonSkillsResponse(
    @SerializedName("abilities")
    var abilitiesInfo: List<AbilitiesInfo>
)

data class AbilitiesInfo(
    @SerializedName("ability")
    var ability: Ability
)

data class Ability(
    @SerializedName("name")
    var name: String,

    @SerializedName("url")
    var url: String
)