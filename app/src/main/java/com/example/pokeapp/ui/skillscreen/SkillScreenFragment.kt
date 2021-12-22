package com.example.pokeapp.ui.skillscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.APIResult
import com.example.pokeapp.databinding.FragmentSkillsScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkillScreenFragment : Fragment(R.layout.fragment_skills_screen) {

    private val args: SkillScreenFragmentArgs by navArgs()

    private lateinit var binding: FragmentSkillsScreenBinding

    private val skillsScreenViewModel: SkillScreenViewModel by viewModels()

    private lateinit var skillsAdapter: SkillsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSkillsScreenBinding.bind(view)

        initialize()
        initObservers()
    }

    private fun initialize() {
        skillsScreenViewModel.getPokemonSkills(args.pokemonId)

        binding.title.text = args.pokemonId
        binding.skillsList.layoutManager = LinearLayoutManager(context)

        skillsAdapter = SkillsAdapter()
    }

    private fun initObservers() {
        skillsScreenViewModel.pokemonSkills.observe(viewLifecycleOwner, {
            if (it is APIResult.Success) {
                skillsAdapter.list =
                    it.data.abilitiesInfo.map { info -> info.ability.name } as ArrayList<String>
                binding.skillsList.adapter = skillsAdapter
            }
        })
    }
}