package com.example.loginandregistertask.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://admin-cp.nabra-kwt.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api:ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}