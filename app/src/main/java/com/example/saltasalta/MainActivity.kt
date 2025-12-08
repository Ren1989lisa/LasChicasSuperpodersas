    package com.example.saltasalta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.data.repository.AuthRepository
import com.example.saltasalta.navigation.Screen
import com.example.saltasalta.navigation.NavGraph
import com.example.saltasalta.ui.screens.EditProfileScreen
import com.example.saltasalta.ui.screens.GameScreen
import com.example.saltasalta.ui.screens.HomeScreen
import com.example.saltasalta.ui.screens.LoginScreen
import com.example.saltasalta.ui.screens.RegisterScreen
import com.example.saltasalta.ui.theme.SaltaSaltaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val authRepository = AuthRepository()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaltaSaltaTheme {
                AppNavigation(authRepository = authRepository)
            }
        }
    }
}

@Composable
fun AppNavigation(authRepository: AuthRepository) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    
    // Estado para controlar si el usuario está autenticado
    var isAuthenticated by remember { mutableStateOf(false) }
    var currentUser by remember { mutableStateOf<UserResponse?>(null) }
    var showRegisterScreen by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    
    // Función para mostrar mensajes
    fun showMessage(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
    
    // Si está autenticado, mostrar navegación principal
    if (isAuthenticated && currentUser != null) {
        NavGraph(
            navController = navController,
            currentUser = currentUser!!,
            onLogout = {
                isAuthenticated = false
                currentUser = null
                showRegisterScreen = false
            }
        )
    } else {
        // Pantalla de login/registro
        if (showRegisterScreen) {
            RegisterScreen(
                modifier = Modifier.fillMaxSize(),
                onRegisterClick = { username, password, confirmPassword ->
                    if (password != confirmPassword) {
                        showMessage("Las contraseñas no coinciden")
                        return@RegisterScreen
                    }
                    
                    isLoading = true
                    coroutineScope.launch {
                        val result = authRepository.register(username, password)
                        isLoading = false
                        
                        when (result) {
                            is com.example.saltasalta.data.repository.AuthResult.Success -> {
                                showMessage("Usuario registrado exitosamente")
                                currentUser = result.user
                                isAuthenticated = true
                                showRegisterScreen = false
                            }
                            is com.example.saltasalta.data.repository.AuthResult.Error -> {
                                showMessage(result.message)
                            }
                        }
                    }
                },
                onBackToLogin = {
                    showRegisterScreen = false
                }
            )
        } else {
            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                onLoginClick = { username, password ->
                    isLoading = true
                    coroutineScope.launch {
                        val result = authRepository.login(username, password)
                        isLoading = false
                        
                        when (result) {
                            is com.example.saltasalta.data.repository.AuthResult.Success -> {
                                showMessage("Login exitoso")
                                currentUser = result.user
                                isAuthenticated = true
                            }
                            is com.example.saltasalta.data.repository.AuthResult.Error -> {
                                showMessage(result.message)
                            }
                        }
                    }
                },
                onRegisterClick = {
                    showRegisterScreen = true
                }
            )
        }
    }
    
    // Snackbar para mostrar mensajes
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.fillMaxSize()
    )
}
