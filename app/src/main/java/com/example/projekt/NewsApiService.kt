package com.example.pubtech_project

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("news")
    suspend fun getNews(@Query("limit") limit: Int = 100): NewsResponse

    companion object {
        fun create(): NewsApiService {
            val test = Retrofit.Builder()
                .baseUrl("https://newsapp-be-ext.br.de/api/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApiService::class.java)
            return test
        }
    }
}