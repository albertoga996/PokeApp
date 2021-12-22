package com.example.pokeapp.ui.evolutionaryline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.evolutionaryline.EvolutionaryLineRepository
import com.example.pokeapp.data.retrofit.PokemonEvolutionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EvolutionaryLineViewModel @Inject constructor(
    private val evolutionaryLineRepository: EvolutionaryLineRepository
) : ViewModel() {

    private val _pokemonEvolution = MutableLiveData<APIResult<PokemonEvolutionResponse>>()
    val pokemonEvolution: LiveData<APIResult<PokemonEvolutionResponse>> = _pokemonEvolution

    private val _changedToFavorite = MutableLiveData<APIResult<Boolean>>()
    val changedToFavorite: LiveData<APIResult<Boolean>> = _changedToFavorite

    fun getEvolutionaryLine(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = evolutionaryLineRepository.getEvolutionaryLine(url)
            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _pokemonEvolution.value = result
                }
            }
        }
    }

    fun changeToFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = evolutionaryLineRepository.changeToFavorite(id)
            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _changedToFavorite.value = result
                }
            }
        }

    }
}