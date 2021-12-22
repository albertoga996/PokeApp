package com.example.pokeapp.di

import android.content.Context
import com.example.pokeapp.R
import com.example.pokeapp.data.retrofit.RetrofitService
import com.example.pokeapp.db.PokeDatabase
import com.example.pokeapp.db.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext appContext: Context): RetrofitService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.ppal_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDatabase: PokeDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao
    }

    @Provides
    @Singleton
    fun providePokeDatabase(@ApplicationContext appContext: Context): PokeDatabase {
        return PokeDatabase.getInstance(appContext)
    }

}