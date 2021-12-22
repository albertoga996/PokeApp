package com.example.pokeapp.ui.speciesinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.PokemonSpecieResponse
import com.example.pokeapp.data.speciesinfo.SpeciesInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SpeciesInfoViewModel @Inject constructor(
    private val speciesInfoRepository: SpeciesInfoRepository
) : ViewModel() {

    private val _pokemonSpecs = MutableLiveData<PokemonSpecieResponse>()
    val pokemonSpecs: LiveData<PokemonSpecieResponse> = _pokemonSpecs

    fun getPokemonInf(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = speciesInfoRepository.getPokemonInfo(id)
            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _pokemonSpecs.value = requireNotNull(result.data)
                }
            }
        }

    }
}