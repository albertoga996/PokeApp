package com.example.pokeapp.ui.evolutionaryline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.data.retrofit.Chain
import com.example.pokeapp.databinding.FragmentEvolutionaryLineBinding
import com.example.pokeapp.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EvolutionaryLineFragment : Fragment(R.layout.fragment_evolutionary_line) {

    private val args: EvolutionaryLineFragmentArgs by navArgs()

    private lateinit var binding: FragmentEvolutionaryLineBinding

    private val evolutionaryLineViewModel: EvolutionaryLineViewModel by viewModels()

    private lateinit var evolutionAdapter: EvolutionaryAdapter

    private var selectedItem: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEvolutionaryLineBinding.bind(view)

        initialize()
        initObserver()
    }

    private fun initialize() {
        evolutionaryLineViewModel.getEvolutionaryLine(args.evolutionUrl)

        binding.evolutionaryList.layoutManager = LinearLayoutManager(context)
        evolutionAdapter = EvolutionaryAdapter()
    }

    private fun initObserver() {
        evolutionaryLineViewModel.pokemonEvolution.observe(viewLifecycleOwner, {
            if (it is APIResult.Success) {
                recursive(it.data.chain)
                evolutionAdapter.list = array
                evolutionAdapter.setOnItemClickListener(object :
                    EvolutionaryAdapter.OnItemClickListener {
                    override fun onItemClick(itemId: String) {
                        Timber.i("Pokemon to check: $itemId")
                        selectedItem = itemId
                        evolutionaryLineViewModel.changeToFavorite(itemId)
                    }
                })
                binding.evolutionaryList.adapter = evolutionAdapter
            }
        })

        evolutionaryLineViewModel.changedToFavorite.observe(viewLifecycleOwner, {
            if (it is APIResult.Success && selectedItem != null) {
                navigateTo(
                    EvolutionaryLineFragmentDirections.actionEvolutionaryLineFragmentToDashboardFragment(
                        selectedItem, it.data
                    )
                )
                selectedItem = null
            }
        })
    }

    private var array = arrayListOf<String>()
    private fun recursive(data: Chain) {
        array = array.plus(data.species.name) as ArrayList<String>
        if (!data.evolvesTo.isNullOrEmpty()) {
            recursive(data.evolvesTo[0])
        }
    }
}