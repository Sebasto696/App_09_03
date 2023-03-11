package com.example.app_03_09_2023.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_03_09_2023.presentations.screens.AddScreen
import com.example.app_03_09_2023.presentations.screens.Jackets.JacketsViewModel
import com.example.app_03_09_2023.presentations.screens.AppJackets
import com.example.app_03_09_2023.presentations.screens.Login.LoginScreen
import com.example.app_03_09_2023.presentations.screens.Registration.RegistrationScreen
import com.example.app_03_09_2023.presentations.screens.Login.LoginViewModel
import com.example.app_03_09_2023.presentations.screens.Registration.RegisterViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.LoginScreen.route
    ) {
        val viewModel = LoginViewModel()
        composable(route = Destinations.LoginScreen.route) {
            if (viewModel.state.value.successLogin) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(Destinations.HomeScreen.createRoute(viewModel.state.value.email))
                    ///modificamos el historial de navegacion para no poder regresar
                    {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }//fin popup
                    }//fin modificacion
                }//fin launcheffect
            } else {
                LoginScreen(
                    navController,
                    state = viewModel.state.value,
                    onLogin = viewModel::login,
                    onNavigateRegister = { navController.navigate(Destinations.RegistrationScreen.route) },
                    onNavigateForgot = { navController.navigate(Destinations.ForgotPassword.route) },
                    onDismissDialog = viewModel::hideErrorDialog
                )
            }//fin if
        }//fin composable login
        val viewModel2 = RegisterViewModel()
        composable("RegistrationScreen")
        {

            RegistrationScreen(
                navController,
                state = viewModel2.state.value,
                onRegister = viewModel2::register,
                onBack = { navController.popBackStack() },
                onDismissDialog = viewModel2::hideErrorDialog
            )
        }
        composable(route = Destinations.HomeScreen.route){ AppJackets(JacketsViewModel(), navController)}
        composable(route = Destinations.AddScreen.route){ AddScreen(navController,)}
    }
}