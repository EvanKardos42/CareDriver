package com.enigmastudios.caredrivers.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FetchData {
    val fetcher = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object{
        val BASE_URL = "https://storage.googleapis.com/"
    }
}