package com.example.pokeapp.ui.dashboard

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.databinding.FragmentDashboardBinding
import com.example.pokeapp.db.entities.Pokemon
import com.example.pokeapp.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val args: DashboardFragmentArgs by navArgs()

    private lateinit var binding: FragmentDashboardBinding

    private val dashboardViewModel by viewModels<DashboardViewModel>()

    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDashboardBinding.bind(view)

        initialize()
        initObservers()
    }

    private fun initialize() {
        dashboardViewModel.getPokemonsData()

        binding.principalList.layoutManager = LinearLayoutManager(context)
        pokemonAdapter = PokemonAdapter()
    }

    private fun initObservers() {
        dashboardViewModel.pokemonsData.observe(viewLifecycleOwner, {
            if (it is APIResult.Success) {
                Timber.i("Information obtained")
                pokemonAdapter.list = it.data as ArrayList<Pokemon>
                pokemonAdapter.setOnItemClickListener(object : PokemonAdapter.OnItemClickListener {
                    override fun onItemClick(itemId: String) {
                        Timber.i("Pokemon selected:${itemId.replaceBeforeLast(" ", "").removePrefix(" ")}")
                        navigateTo(
                            DashboardFragmentDirections.actionDashboardFragmentToSpeciesInfoFragment(
                                itemId.replaceBeforeLast(" ", "").removePrefix(" ")
                            )
                        )
                    }
                })

                args.favoritePokemon?.let { value ->
                    val newValue = (if (args.favorite) "favorite - " else "error - ").plus(value)
                    pokemonAdapter.changeName(value, newValue)
                    object : CountDownTimer(5000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                        }

                        override fun onFinish() {
                            pokemonAdapter.changeName(newValue, value)
                        }
                    }.start()
                }

                binding.principalList.adapter = pokemonAdapter
            }
        })
    }
}