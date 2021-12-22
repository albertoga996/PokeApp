package com.example.pokeapp.ui.skillscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSkillsResponse
import com.example.pokeapp.data.skillscreen.SkillScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SkillScreenViewModel @Inject constructor(
    private val skillScreenRepository: SkillScreenRepository
) : ViewModel() {

    private val _pokemonSkills = MutableLiveData<APIResult<PokemonSkillsResponse>>()
    val pokemonSkills: LiveData<APIResult<PokemonSkillsResponse>> = _pokemonSkills

    fun getPokemonSkills(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = skillScreenRepository.getPokemonSkills(id)
            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _pokemonSkills.value = result
                }
            }
        }
    }
}