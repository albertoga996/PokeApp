package com.example.pokeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokeapp.Defs
import com.example.pokeapp.db.entities.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokeDatabase : RoomDatabase() {

    abstract val pokemonDao: PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokeDatabase? = null

        fun getInstance(context: Context): PokeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokeDatabase::class.java,
                        Defs.POKEMON_DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}