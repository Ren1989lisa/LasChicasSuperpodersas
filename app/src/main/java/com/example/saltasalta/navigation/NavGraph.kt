package com.example.saltasalta.navigation

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saltasalta.data.models.UserResponse

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Game : Screen("game")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    currentUser: UserResponse,
    onLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Profile.route
    ) {
        composable(Screen.Profile.route) {
            EditProfileScreen(
                currentUser = currentUser,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = { _, _, _ -> },
                onLogoutClick = onLogout
            )
        }

        composable(Screen.Game.route) {
            GameScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Placeholders para evitar referencias a pantallas eliminadas.
@Composable
private fun EditProfileScreen(
    currentUser: UserResponse,
    onBackClick: () -> Unit,
    onSaveClick: (String, String, String) -> Unit,
    onLogoutClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Pantalla de perfil no disponible",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun GameScreen(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Pantalla de juego no disponible",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

