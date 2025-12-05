package com.example.saltasalta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.saltasalta.ui.theme.SaltaSaltaTheme
import com.example.saltasalta.ui.screens.LoginScreen
import com.example.saltasalta.ui.screens.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaltaSaltaTheme {
                // Estado para controlar qué pantalla mostrar
                var showRegisterScreen by remember { mutableStateOf(false) }
                
                if (showRegisterScreen) {
                    // Pantalla de registro
                    RegisterScreen(
                        modifier = Modifier.fillMaxSize(),
                        onRegisterClick = { username, password, confirmPassword ->
                            // TODO: Implementar lógica de registro
                            // Por ahora, volvemos a la pantalla de login después del registro
                            showRegisterScreen = false
                        },
                        onBackToLogin = {
                            // Volver a la pantalla de login
                            showRegisterScreen = false
                        }
                    )
                } else {
                    // Pantalla de login
                    LoginScreen(
                        modifier = Modifier.fillMaxSize(),
                        onLoginClick = { username, password ->
                            // TODO: Implementar lógica de login
                        },
                        onRegisterClick = {
                            // Navegar a pantalla de registro
                            showRegisterScreen = true
                        }
                    )
                }
            }
        }
    }
}
