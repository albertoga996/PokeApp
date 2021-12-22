package com.example.pokeapp.data.skillscreen

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSkillsResponse
import com.example.pokeapp.data.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class SkillScreenSource @Inject constructor(
    private val retrofitService: RetrofitService
) {
    suspend fun getPokemonSkills(id: String): APIResult<PokemonSkillsResponse> =
        withContext(Dispatchers.IO) {
            try {
                APIResult.Success(retrofitService.getPokemonSkills(id))
            } catch (e: HttpException) {
                APIResult.Error(e.message())
            }
        }
}