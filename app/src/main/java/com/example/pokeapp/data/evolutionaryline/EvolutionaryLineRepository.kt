package com.example.pokeapp.data.evolutionaryline

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonEvolutionResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EvolutionaryLineRepository @Inject constructor(
    private val remoteSource: EvolutionaryLineSource
) {
    suspend fun getEvolutionaryLine(url: String): APIResult<PokemonEvolutionResponse> {
        return remoteSource.getEvolutionaryLine(url)
    }

    suspend fun changeToFavorite(id: String): APIResult<Boolean> {
        return remoteSource.changeToFavorite(id)
    }
}