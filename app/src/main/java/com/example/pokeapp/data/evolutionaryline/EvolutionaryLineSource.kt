package com.example.pokeapp.data.evolutionaryline

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonEvolutionResponse
import com.example.pokeapp.data.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class EvolutionaryLineSource @Inject constructor(
    private val retrofitService: RetrofitService
) {

    suspend fun getEvolutionaryLine(url: String): APIResult<PokemonEvolutionResponse> =
        withContext(Dispatchers.IO) {
            try {
                APIResult.Success(retrofitService.getEvolutionaryLine(url))
            } catch (e: HttpException) {
                APIResult.Error(e.message())
            }
        }

    suspend fun changeToFavorite(id: String): APIResult<Boolean> = withContext(Dispatchers.IO) {
        try {
            delay(3000)
            val random = Random.nextInt(100)
            if (random == 0) {
                throw Exception("Fail")
            }
            Timber.i("The random number is $random")
            if (random % 2 == 0) {
                APIResult.Success(true)
            } else {
                APIResult.Success(false)
            }
        } catch (e: Exception) {
            APIResult.Error("Launch an exception")
        }
    }
}