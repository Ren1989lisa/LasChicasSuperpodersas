package com.example.saltasalta.data.repository

import com.example.saltasalta.data.api.RetrofitClient
import com.example.saltasalta.data.models.UserRequest
import com.example.saltasalta.data.models.UserResponse

sealed class AuthResult {
    data class Success(val user: UserResponse) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthRepository {
    private val apiService = RetrofitClient.apiService
    
    suspend fun login(username: String, password: String): AuthResult {
        return try {
            val response = apiService.loginUsuario(
                UserRequest(username = username, password = password)
            )
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.usuario != null) {
                    AuthResult.Success(body.usuario!!)
                } else {
                    AuthResult.Error(body?.error ?: "Error desconocido")
                }
            } else {
                // Intentar parsear el error del cuerpo de la respuesta
                val errorBody = response.errorBody()?.string()
                // El servidor Flask devuelve JSON con campo "error"
                // Retrofit ya parsea esto, pero si falla, usamos el mensaje directo
                val errorMessage = if (errorBody != null && errorBody.contains("\"error\"")) {
                    // Extraer el mensaje de error del JSON manualmente
                    try {
                        val start = errorBody.indexOf("\"error\":\"") + 9
                        val end = errorBody.indexOf("\"", start)
                        if (start > 8 && end > start) {
                            errorBody.substring(start, end)
                        } else {
                            errorBody
                        }
                    } catch (e: Exception) {
                        errorBody
                    }
                } else {
                    errorBody ?: "Error: ${response.code()} - ${response.message()}"
                }
                AuthResult.Error(errorMessage)
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }
    
    suspend fun register(username: String, password: String): AuthResult {
        return try {
            val response = apiService.registrarUsuario(
                UserRequest(username = username, password = password)
            )
            
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.usuario != null) {
                    AuthResult.Success(body.usuario!!)
                } else {
                    AuthResult.Error(body?.error ?: "Error desconocido")
                }
            } else {
                // Intentar parsear el error del cuerpo de la respuesta
                val errorBody = response.errorBody()?.string()
                // El servidor Flask devuelve JSON con campo "error"
                // Retrofit ya parsea esto, pero si falla, usamos el mensaje directo
                val errorMessage = if (errorBody != null && errorBody.contains("\"error\"")) {
                    // Extraer el mensaje de error del JSON manualmente
                    try {
                        val start = errorBody.indexOf("\"error\":\"") + 9
                        val end = errorBody.indexOf("\"", start)
                        if (start > 8 && end > start) {
                            errorBody.substring(start, end)
                        } else {
                            errorBody
                        }
                    } catch (e: Exception) {
                        errorBody
                    }
                } else {
                    errorBody ?: "Error: ${response.code()} - ${response.message()}"
                }
                AuthResult.Error(errorMessage)
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }
}

