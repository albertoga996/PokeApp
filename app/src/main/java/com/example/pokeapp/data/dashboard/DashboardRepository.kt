package com.example.pokeapp.data.dashboard

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.db.PokemonDao
import com.example.pokeapp.db.entities.Pokemon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepository @Inject constructor(
    private val remoteSource: DashboardSource,
    private val localSource: PokemonDao
) {

    suspend fun getPokemonsData(): APIResult<List<Pokemon>> {
        val result = remoteSource.getPokemons()
        if (result is APIResult.Success) {
            localSource.insertAll(result.data)
        }
        return result
    }
}