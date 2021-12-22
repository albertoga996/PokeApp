package com.example.pokeapp.data.dashboard

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.RetrofitService
import com.example.pokeapp.db.entities.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class DashboardSource @Inject constructor(
    private val retrofitService: RetrofitService
) {

    suspend fun getPokemons(): APIResult<List<Pokemon>> = withContext(Dispatchers.IO) {
        try {
            APIResult.Success(retrofitService.getPokemons().data)
        } catch (e: HttpException) {
            APIResult.Error(e.message())
        }
    }
}