package com.example.saltasalta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.ui.screens.EditProfileScreen
import com.example.saltasalta.ui.screens.GameScreen
import com.example.saltasalta.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
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
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                currentUser = currentUser,
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToGame = {
                    navController.navigate(Screen.Game.route)
                }
            )
        }

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

