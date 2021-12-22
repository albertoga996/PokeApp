package com.example.pokeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokeapp.Defs
import com.google.gson.annotations.SerializedName

@Entity(tableName = Defs.POKEMON_TABLE_NAME)
data class Pokemon(

    @SerializedName("name")
    @ColumnInfo(name = "pokemon_name")
    @PrimaryKey
    var name: String,

    @SerializedName("url")
    @ColumnInfo(name = "pokemon_url_info")
    var urlInfo: String
)
