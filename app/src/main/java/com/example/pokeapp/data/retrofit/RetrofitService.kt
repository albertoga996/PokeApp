package com.example.pokeapp.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("pokemon?limit=151")
    suspend fun getPokemons(): PokemonResponse

    @GET("pokemon-species/{name}/")
    suspend fun getSpecificPokemon(@Path("name") name: String): PokemonSpecieResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonSkills(@Path("name") name: String): PokemonSkillsResponse

    @GET("{url}")
    suspend fun getEvolutionaryLine(@Path("url") url: String): PokemonEvolutionResponse
}