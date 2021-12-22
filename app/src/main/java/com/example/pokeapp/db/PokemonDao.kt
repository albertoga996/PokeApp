package com.example.pokeapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.pokeapp.Defs
import com.example.pokeapp.db.entities.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Insert(onConflict = REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(list: List<Pokemon>)

    @Query("SELECT * FROM ${Defs.POKEMON_TABLE_NAME} ORDER BY pokemon_name DESC")
    fun getAllPokemons(): List<Pokemon>
}