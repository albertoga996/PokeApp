package com.example.pokeapp.ui.skillscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ElementPokemonBinding

class SkillsAdapter() : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    var list = arrayListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ElementPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ElementPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skill: String) {
            binding.name.text = skill
        }
    }
}