package com.example.saltasalta.data.repository

import com.example.saltasalta.data.api.RetrofitClient
import com.example.saltasalta.data.models.UpdateUserRequest
import com.example.saltasalta.data.models.UserRequest
import com.example.saltasalta.data.models.UserResponse

sealed class AuthResult {
    data class Success(val user: UserResponse) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

class AuthRepository {
    private val apiService = RetrofitClient.apiService
    private fun parseError(responseCode: Int, message: String?, errorBody: String?): String {
        val raw = errorBody ?: message ?: "Error $responseCode"
        return try {
            if (raw.contains("\"error\"")) {
                val start = raw.indexOf("\"error\":\"") + 9
                val end = raw.indexOf("\"", start)
                if (start > 8 && end > start) raw.substring(start, end) else raw
            } else raw
        } catch (_: Exception) {
            raw
        }
    }
    
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
                val error = parseError(response.code(), response.message(), response.errorBody()?.string())
                AuthResult.Error(error)
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
                val error = parseError(response.code(), response.message(), response.errorBody()?.string())
                AuthResult.Error(error)
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }

    suspend fun updateUser(id: Int, username: String?, password: String?): AuthResult {
        return try {
            val response = apiService.actualizarUsuario(
                id = id,
                body = UpdateUserRequest(username = username, password = password)
            )
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.usuario != null) {
                    AuthResult.Success(body.usuario!!)
                } else {
                    AuthResult.Error(body?.error ?: "Error desconocido")
                }
            } else {
                val error = parseError(response.code(), response.message(), response.errorBody()?.string())
                AuthResult.Error(error)
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }

    suspend fun deleteUser(id: Int): AuthResult {
        return try {
            val response = apiService.eliminarUsuario(id)
            if (response.isSuccessful) {
                AuthResult.Success(
                    UserResponse(id = -1, username = "", fecha_registro = null)
                )
            } else {
                val error = parseError(response.code(), response.message(), response.errorBody()?.string())
                AuthResult.Error(error)
            }
        } catch (e: Exception) {
            AuthResult.Error("Error de conexión: ${e.message}")
        }
    }
}

