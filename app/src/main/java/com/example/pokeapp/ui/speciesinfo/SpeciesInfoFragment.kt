package com.example.pokeapp.ui.speciesinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentSpeciesInfoBinding
import com.example.pokeapp.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeciesInfoFragment : Fragment(R.layout.fragment_species_info) {

    private val args: SpeciesInfoFragmentArgs by navArgs()

    private lateinit var binding: FragmentSpeciesInfoBinding

    private val speciesInformationViewModel by viewModels<SpeciesInfoViewModel>()

    private var evolutionURl: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSpeciesInfoBinding.bind(view)

        initialize()
        initObserves()
    }

    private fun initialize() {
        speciesInformationViewModel.getPokemonInf(args.pokemonId)

        binding.btnConsultaHabilidades.setOnClickListener {
            navigateTo(
                SpeciesInfoFragmentDirections.actionSpeciesInfoFragmentToSkillScreenFragment(
                    args.pokemonId
                )
            )
        }
        binding.btnLineaEvolutiva.setOnClickListener {
            evolutionURl?.let {
                navigateTo(
                    SpeciesInfoFragmentDirections.actionSpeciesInfoFragmentToEvolutionaryLineFragment(
                        it.replace(getString(R.string.ppal_url), "")
                    )
                )
            }
        }
    }

    private fun initObserves() {
        speciesInformationViewModel.pokemonSpecs.observe(viewLifecycleOwner, {
            binding.title.text = args.pokemonId
            binding.felicidadBase.text = getString(R.string.felididad_base, it.baseHappiness)
            binding.ratioCaptura.text = getString(R.string.ratio_captura, it.captureRate)
            binding.gruposDeHuevo.text = getString(
                R.string.grupos_de_huevo,
                it.eggGroups.joinToString(",") { group -> group.name }
            )

            evolutionURl = it.evolutionChain.url
        })
    }
}