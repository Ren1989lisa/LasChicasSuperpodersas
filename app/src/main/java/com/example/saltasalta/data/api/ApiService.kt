package com.example.saltasalta.data.api

import com.example.saltasalta.data.models.ApiResponse
import com.example.saltasalta.data.models.UserRequest
import com.example.saltasalta.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/usuarios/registro")
    suspend fun registrarUsuario(@Body user: UserRequest): Response<ApiResponse<UserResponse>>
    
    @POST("api/usuarios/login")
    suspend fun loginUsuario(@Body credentials: UserRequest): Response<ApiResponse<UserResponse>>
}

