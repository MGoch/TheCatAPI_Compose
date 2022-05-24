package com.example.catapp.service

import com.example.catapp.model.CatImage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatAPI {

    @GET("/v1/images/search?limit=10&page=1&order=Desc")
    suspend fun getCats() : List<CatImage>

    companion object {
        private var apiService: CatAPI? = null
        fun getInstance() : CatAPI {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.thecatapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CatAPI::class.java)
            }
            return apiService!!
        }
    }
}