package com.example.saltasalta.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.ui.screens.EditProfileScreen
import com.example.saltasalta.ui.screens.GameMenuScreen
import com.example.saltasalta.ui.screens.GamePlayScreen
import com.example.saltasalta.ui.screens.LoginScreen
import com.example.saltasalta.ui.screens.RegisterScreen
import com.example.saltasalta.ui.screens.TopPlayersScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Menu : Screen("menu")
    object Game : Screen("game")
    object Profile : Screen("profile")
    object TopPlayers : Screen("topPlayers")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    currentUser: MutableState<UserResponse?>,
    onAuth: (UserResponse) -> Unit,
    onLogin: (String, String) -> Unit,
    onRegister: (String, String, String) -> Unit,
    onSaveProfile: (String, String, String) -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = onLogin,
                onRegisterClick = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = onRegister,
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Menu.route) {
            GameMenuScreen(
                onPlayClick = { navController.navigate(Screen.Game.route) },
                onProfileClick = { navController.navigate(Screen.Profile.route) },
                onTopClick = { navController.navigate(Screen.TopPlayers.route) }
            )
        }
        composable(Screen.Game.route) {
            GamePlayScreen()
        }
        composable(Screen.Profile.route) {
            val user = currentUser.value
            EditProfileScreen(
                currentUser = user,
                onBackClick = { navController.popBackStack() },
                onSaveClick = onSaveProfile,
                onLogoutClick = {
                    onLogout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onDeleteClick = {
                    onDeleteAccount()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.TopPlayers.route) {
            TopPlayersScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
