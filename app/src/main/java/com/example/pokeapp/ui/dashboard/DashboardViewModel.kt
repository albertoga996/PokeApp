package com.example.pokeapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.dashboard.DashboardRepository
import com.example.pokeapp.db.PokemonDao
import com.example.pokeapp.db.entities.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val localSource: PokemonDao
) : ViewModel() {

    private val _pokemonsData = MutableLiveData<APIResult<List<Pokemon>>>()
    val pokemonsData: LiveData<APIResult<List<Pokemon>>> = _pokemonsData

    fun getPokemonsData() {
        viewModelScope.launch(Dispatchers.IO) {
            var result: APIResult<List<Pokemon>> = APIResult.Error("")
            val source = async {
                val temp = localSource.getAllPokemons()
                result = if (temp.isNotEmpty()) {
                    APIResult.Success(temp)
                } else {
                    dashboardRepository.getPokemonsData()
                }
            }
            source.await()

            withContext(Dispatchers.Main) {
                if (result is APIResult.Success) {
                    _pokemonsData.value = result
                }
            }
        }
    }
}