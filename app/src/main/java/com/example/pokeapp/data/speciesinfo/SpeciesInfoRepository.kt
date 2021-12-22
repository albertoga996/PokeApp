package com.example.pokeapp.data.speciesinfo

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSpecieResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesInfoRepository @Inject constructor(
    private val remoteSource: SpeciesInfoSource
) {
    suspend fun getPokemonInfo(id: String): APIResult<PokemonSpecieResponse> {
        return remoteSource.getPokemonInfo(id)
    }
}