package com.example.pokeapp.ui.evolutionaryline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ElementPokemonBinding

class EvolutionaryAdapter() :
    RecyclerView.Adapter<EvolutionaryAdapter.ViewHolder>() {

    private var onClickListener: OnItemClickListener? = null

    var list = arrayListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.onClickListener = mOnItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EvolutionaryAdapter.ViewHolder {
        return ViewHolder(
            ElementPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EvolutionaryAdapter.ViewHolder, position: Int) {
        holder.bind(list[position], onClickListener)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ElementPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: String, clickListener: OnItemClickListener?) {
            binding.name.text = pokemon
            clickListener?.let {
                itemView.setOnClickListener { _ ->
                    it.onItemClick(pokemon)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemId: String)
    }

}