package com.example.pokeapp.data.skillscreen

import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSkillsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillScreenRepository @Inject constructor(
    private val remoteSource: SkillScreenSource
) {

    suspend fun getPokemonSkills(id: String): APIResult<PokemonSkillsResponse> {
        return remoteSource.getPokemonSkills(id)
    }
}