package com.example.saltasalta.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // ⚠️ IMPORTANTE: Cambia esta IP por la tuya (192.168.0.8)
    // Si tu servidor está en otra IP, actualiza este valor
    private const val BASE_URL = "http://192.168.0.8:5000/"
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

