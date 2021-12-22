package com.example.pokeapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ElementPokemonBinding
import com.example.pokeapp.db.entities.Pokemon

class PokemonAdapter() :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var onClickListener: OnItemClickListener? = null

    var list = arrayListOf<Pokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.onClickListener = mOnItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        return ViewHolder(
            ElementPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        holder.bind(list[position].name, position, onClickListener)
    }

    override fun getItemCount() = list.size

    fun changeName(key: String, newValue: String) {
        val element = list.find { it.name == key }
        element?.let {
            val position = list.indexOf(element)
            it.name = newValue
            list[position] = it
            notifyItemChanged(position)
        }
    }

    inner class ViewHolder(private val binding: ElementPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemId: String, position: Int, clickListener: OnItemClickListener?) {
            binding.name.text = itemId
            clickListener?.let {
                itemView.setOnClickListener { _ ->
                    it.onItemClick(itemId)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemId: String)
    }

}