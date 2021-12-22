package com.example.pokeapp.data.speciesinfo

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSpecieResponse
import com.example.pokeapp.data.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class SpeciesInfoSource @Inject constructor(
    private val retrofitService: RetrofitService
) {

    suspend fun getPokemonInfo(id: String): APIResult<PokemonSpecieResponse> =
        withContext(Dispatchers.IO) {
            try {
                APIResult.Success(retrofitService.getSpecificPokemon(id))
            } catch (e: HttpException) {
                APIResult.Error(e.message())
            }
        }
}